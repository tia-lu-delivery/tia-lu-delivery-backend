package br.com.fooddelivery.tialudeliveryback.dto;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class CategoriaDTO {

    private Long id;

    private String nomeCategoria;

    private int ordem;

    private Boolean disponivel;

    private CardapioDTO cardapio;

    private List<ProdutoDTO> produtos;

    //-------------

    public CategoriaDTO(CategoriaDTO categoria) {
        BeanUtils.copyProperties(categoria, this);
    }
    public CategoriaDTO() {}

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

    public int getOrdem() {
        return ordem;
    }
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public CardapioDTO getCardapio() {
        return cardapio;
    }
    public void setCardapio(CardapioDTO cardapio) {
        this.cardapio = cardapio;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

}
