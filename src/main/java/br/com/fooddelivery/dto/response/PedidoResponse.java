package com.example.fooddelivery.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {

    private Long id;
    private ClienteResponse cliente;
    private List<ItemPedidoResponse> itens;
    private double total;
    private String status;
    private LocalDateTime dataCriacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResponse getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponse cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedidoResponse> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponse> itens) {
        this.itens = itens;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
