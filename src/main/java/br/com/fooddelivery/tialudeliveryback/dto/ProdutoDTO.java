package br.com.fooddelivery.tialudeliveryback.dto;

public class ProdutoDTO {

    private String idProduto;
    private String nomeProduto;
    private String descricao;
    private Double precoUnitario;
    private String imagemUrl;
    private Boolean disponivel;
    private Integer estoque;

    // Construtor vazio
    public ProdutoDTO() {
    }

    // Construtor completo
    public ProdutoDTO(String idProduto, String nomeProduto, String descricao, Double precoUnitario,
                      String imagemUrl, Boolean disponivel, Integer estoque) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.imagemUrl = imagemUrl;
        this.disponivel = disponivel;
        this.estoque = estoque;
    }

    // Getters e Setters
    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
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
