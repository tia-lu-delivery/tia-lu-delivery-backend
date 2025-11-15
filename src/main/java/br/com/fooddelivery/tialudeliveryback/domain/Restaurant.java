package br.com.fooddelivery.tialudeliveryback.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "avaliacao_media")
    private Double avaliacaoMedia;

    @Column(name = "tempo_medio_entrega")
    private String tempoMedioEntrega;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MenuItem> menu;

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getAvaliacaoMedia() { return avaliacaoMedia; }
    public void setAvaliacaoMedia(Double avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; }

    public String getTempoMedioEntrega() { return tempoMedioEntrega; }
    public void setTempoMedioEntrega(String tempoMedioEntrega) { this.tempoMedioEntrega = tempoMedioEntrega; }

    public List<MenuItem> getMenu() { return menu; }
    public void setMenu(List<MenuItem> menu) { this.menu = menu; }
}
