package com.example.fooddelivery.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoRequest {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private int quantidade;

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
