package br.com.fooddelivery.tialudeliveryback.dto;

public class ItemPedidoDTO {

    private Long id;
    private String produto;
    private Integer quantidade;
    private Double preco;

    public ItemPedidoDTO() {}

    public ItemPedidoDTO(Long id, String produto, Integer quantidade, Double preco) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}