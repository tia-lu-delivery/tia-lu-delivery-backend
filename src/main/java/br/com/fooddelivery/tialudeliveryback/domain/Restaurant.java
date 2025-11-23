package br.com.fooddelivery.tialudeliveryback.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    private String id;

    private String nome;
    private Double avaliacaoMedia;
    private String tempoMedioEntrega;

    // Construtor padrão
    public Restaurant() {}

    // Construtor completo para testes e DTOs
    public Restaurant(String id, String nome, Double avaliacaoMedia, String tempoMedioEntrega) {
        this.id = id;
        this.nome = nome;
        this.avaliacaoMedia = avaliacaoMedia;
        this.tempoMedioEntrega = tempoMedioEntrega;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getAvaliacaoMedia() { return avaliacaoMedia; }
    public void setAvaliacaoMedia(Double avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; }

    public String getTempoMedioEntrega() { return tempoMedioEntrega; }
    public void setTempoMedioEntrega(String tempoMedioEntrega) { this.tempoMedioEntrega = tempoMedioEntrega; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
