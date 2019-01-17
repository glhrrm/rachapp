package rachapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Evento {

    private String nome;
    private LocalDate dataCadastro;
    private List<Item> itens;
    private List<Usuario> participantes;
    private boolean contaAberta;

    public Evento(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.dataCadastro = LocalDate.now();
        this.contaAberta = true;
    }

    public StringBuilder listarParticipantes() {
        StringBuilder listaDeParticipantes = new StringBuilder();
        for (Usuario participante : participantes) {
            listaDeParticipantes.append(participante)
                    .append("\n");
        }
        return listaDeParticipantes;
    }

    public void removerParticipantes(Usuario... usuarios) {
        for (Usuario usuario : usuarios) {
            this.participantes.remove(usuario);
        }
    }

    public StringBuilder listarItens() {
        StringBuilder listaDeItens = new StringBuilder();
        for (Item item : itens) {
            listaDeItens.append(item)
                    .append("\n");
        }
        return listaDeItens;
    }

    public void removerItens(Item... itens) {
        for (Item item : itens) {
            this.itens.remove(item);
        }
    }

    public StringBuilder fecharConta() {
        if (this.contaAberta) {
            for (Usuario participante : participantes) {
                for (Item item : itens) {
                    BigDecimal valorUnitarioCompra = item.recalcularCompra();
                    BigDecimal valorUnitarioConsumo = item.recalcularConsumo();
                    if (participante.getItensComprados().contains(item)) {
                        participante.setSaldo(participante.getSaldo().add(valorUnitarioCompra));
                    }
                    if (participante.getItensConsumidos().contains(item)) {
                        participante.setSaldo(participante.getSaldo().subtract(valorUnitarioConsumo));
                    }
                }
            }
        }
        StringBuilder contaFechada = new StringBuilder();
        for (Usuario participante : participantes) {
            contaFechada.append(participante.getNome())
                        .append(": R$ ")
                        .append(participante.getSaldo())
                        .append("\n");
        }
        this.contaAberta = false;
        return contaFechada;
    }

    // toString
    @Override
    public String toString() {
        return nome.toUpperCase() + "\n"
                + "Criado em " + dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n"
                + participantes.size() + " participantes";
    }

    // equals
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        if (!Objects.equals(this.itens, other.itens)) {
            return false;
        }
        if (!Objects.equals(this.participantes, other.participantes)) {
            return false;
        }
        return true;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public List<Item> getItens() {
        return itens;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public boolean isContaAberta() {
        return contaAberta;
    }

    public void setContaAberta(boolean contaAberta) {
        this.contaAberta = contaAberta;
    }
}
