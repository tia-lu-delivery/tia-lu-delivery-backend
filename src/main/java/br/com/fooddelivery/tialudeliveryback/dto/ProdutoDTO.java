package br.com.fooddelivery.tialudeliveryback.dto;


import org.springframework.beans.BeanUtils;

public class ProdutoDTO {

    private Long id;

    private String nomeProduto;

    private String descricao;

    private Double precoUnitario;

    private String imagemUrl;

    private Boolean disponivel;

    private Integer estoque;

    //--------------------------------

    public ProdutoDTO(ProdutoDTO produto) {
        BeanUtils.copyProperties(produto, this);
    }
    public ProdutoDTO() {}

    //-----------------------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }
    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}
