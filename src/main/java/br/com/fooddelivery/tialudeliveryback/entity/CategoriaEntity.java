package br.com.fooddelivery.tialudeliveryback.entity;

import br.com.fooddelivery.tialudeliveryback.dto.CategoriaDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "CATEGORIA")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCategoria;

    private Integer ordem;

    private Boolean disponivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id", nullable = false)
    private CardapioEntity cardapio;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProdutoEntity> produtos;

    //-------

    public CategoriaEntity(CategoriaDTO categoria) {
        BeanUtils.copyProperties(categoria, this);
    }
    public CategoriaEntity() {}

    //-----------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getOrdem() {
        return ordem;
    }
    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public CardapioEntity getCardapio() {
        return cardapio;
    }
    public void setCardapio(CardapioEntity cardapio) {
        this.cardapio = cardapio;
    }

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    //-------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        CategoriaEntity other = (CategoriaEntity) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
