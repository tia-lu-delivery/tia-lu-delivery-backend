package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class PedidoResponseDTO {

    private Long id;
    private String cliente;
    private Double total;
    private List<ItemPedidoDTO> itens;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(Long id, String cliente, Double total, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}