package br.com.fooddelivery.tialudeliveryback.entity;

import br.com.fooddelivery.tialudeliveryback.dto.CardapioDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CARDAPIO")
public class CardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCardapio;

    private String dataAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estabelecimento_id", referencedColumnName = "id")
    private EstabelecimentoEntity estabelecimento;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL)
    private List<CategoriaEntity> categorias;

    //---------------------------

    public CardapioEntity(CardapioDTO cardapio) {
        BeanUtils.copyProperties(cardapio, this);
    }
    public CardapioEntity() {}

    //-------------------------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCardapio() {
        return nomeCardapio;
    }
    public void setNomeCardapio(String nomeCardapio) {
        this.nomeCardapio = nomeCardapio;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public EstabelecimentoEntity getEstabelecimento() {
        return estabelecimento;
    }
    public void setEstabelecimento(EstabelecimentoEntity estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<CategoriaEntity> getCategorias() {
        return categorias;
    }
    public void setCategorias(List<CategoriaEntity> categorias) {
        this.categorias = categorias;
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