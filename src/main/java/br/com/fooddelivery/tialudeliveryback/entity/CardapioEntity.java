package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;

import java.util.Objects;

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

    //---------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        CardapioEntity other = (CardapioEntity) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}