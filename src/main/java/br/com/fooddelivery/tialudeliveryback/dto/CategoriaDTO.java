package br.com.fooddelivery.tialudeliveryback.dto;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class CategoriaDTO {

    private String idCategoria;
    private String nomeCategoria;
    private int ordem;
    private Boolean disponivel;
    private List<ProdutoDTO> produtos;

    // Construtor vazio
    public CategoriaDTO() {
    }

    // Construtor completo
    public CategoriaDTO(String idCategoria, String nomeCategoria, int ordem, Boolean disponivel, List<ProdutoDTO> produtos) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.ordem = ordem;
        this.disponivel = disponivel;
        this.produtos = produtos;
    }

    // Getters e Setters
    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    private Long id;

    private String nomeCategoria;

    private int ordem;

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

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
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
    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

}
