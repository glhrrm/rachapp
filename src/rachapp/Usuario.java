package rachapp;

import java.math.BigDecimal;
import java.util.*;

public class Usuario {
    
    private String nome;
    private BigDecimal saldo;
    private List<Item> itensConsumidos;
    private List<Item> itensComprados;
    private Evento evento;
    
    public Usuario(String nome, Evento evento) {
        this.nome = nome;
        this.saldo = BigDecimal.valueOf(0);
        this.itensComprados = new ArrayList<>();
        this.itensConsumidos = new ArrayList<>();
        this.evento = evento;
        evento.getParticipantes().add(this);
    }
    
    public void comprar(Item item) {
        if (evento.getItens().contains(item)) {
            this.itensComprados.add(item);
            item.getCompradores().add(this);
        }
    }
    
    public void consumir(Item item) {
        if (evento.getItens().contains(item)) {
            this.itensConsumidos.add(item);
            item.getConsumidores().add(this);
        }
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.saldo, other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.itensConsumidos, other.itensConsumidos)) {
            return false;
        }
        if (!Objects.equals(this.itensComprados, other.itensComprados)) {
            return false;
        }
        return true;
    }
    
    // toString

    @Override
    public String toString() {
        return nome;
    }
    
    // getters e setters

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Item> getItensConsumidos() {
        return itensConsumidos;
    }

    public void setItensConsumidos(List<Item> itensConsumidos) {
        this.itensConsumidos = itensConsumidos;
    }

    public List<Item> getItensComprados() {
        return itensComprados;
    }

    public void setItensComprados(List<Item> itensComprados) {
        this.itensComprados = itensComprados;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
