package br.com.fooddelivery.tialudeliveryback.dto;

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
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
}
}
