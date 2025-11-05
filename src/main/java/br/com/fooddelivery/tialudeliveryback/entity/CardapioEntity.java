package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CARDAPIO")
public class CardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String dataAtualizacao;

    //-------------------------------

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

}