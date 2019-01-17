package rachapp;

import java.math.*;
import java.time.LocalDate;
import java.util.*;

public class Item {
    
    private String nome;
    private BigDecimal valor;
    private LocalDate dataInsercao;
    private List<Usuario> compradores;
    private List<Usuario> consumidores;
    private Evento evento;

    public Item(String nome, double valor, Evento evento) {
        this.nome = nome;
        this.valor = BigDecimal.valueOf(valor);
        this.dataInsercao = LocalDate.now();
        this.compradores = new ArrayList<>();
        this.consumidores = new ArrayList<>();
        this.evento = evento;
        evento.getItens().add(this);
    }
    
    public BigDecimal recalcularCompra() {
        BigDecimal novoValorUnitario = BigDecimal.valueOf(0);
        int qtdCompradores = this.compradores.size();
        if (qtdCompradores > 0) {
            novoValorUnitario = this.valor.divide(BigDecimal.valueOf(qtdCompradores), 2, RoundingMode.HALF_EVEN);
        }
        return novoValorUnitario;
    }
    
    public BigDecimal recalcularConsumo() {
        BigDecimal novoValorUnitario = BigDecimal.valueOf(0);
        int qtdConsumidores = this.consumidores.size();
        if (qtdConsumidores > 0) {
            novoValorUnitario = this.valor.divide(BigDecimal.valueOf(qtdConsumidores), 2, RoundingMode.HALF_EVEN);
        }
        return novoValorUnitario;
    }
    
    // toString
    
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", valor);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.dataInsercao, other.dataInsercao)) {
            return false;
        }
        if (!Objects.equals(this.compradores, other.compradores)) {
            return false;
        }
        if (!Objects.equals(this.consumidores, other.consumidores)) {
            return false;
        }
        if (!Objects.equals(this.evento, other.evento)) {
            return false;
        }
        return true;
    }
    
    // getters e setters

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataInsercao() {
        return dataInsercao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = BigDecimal.valueOf(valor);
    }

    public void setDataInsercao(LocalDate dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public List<Usuario> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Usuario> compradores) {
        this.compradores = compradores;
    }

    public List<Usuario> getConsumidores() {
        return consumidores;
    }

    public void setConsumidores(List<Usuario> consumidores) {
        this.consumidores = consumidores;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
