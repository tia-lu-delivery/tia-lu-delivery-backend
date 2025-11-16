package com.fooddelivery.tialudeliveryback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CardapioRequest {
    
    @NotBlank(message = "O nome do cardápio é obrigatório")
    @Size(max = 100, message = "O nome do cardápio deve ter no máximo 100 caracteres")
    private String nomeCardapio;
    
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;
 
    public CardapioRequest() {}

    public CardapioRequest(String nomeCardapio, String descricao) {
        this.nomeCardapio = nomeCardapio;
        this.descricao = descricao;
    }

    public String getNomeCardapio() { return nomeCardapio; }
    public void setNomeCardapio(String nomeCardapio) { this.nomeCardapio = nomeCardapio; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    @Override
    public String toString() {
        return "CardapioRequest{" +
                "nomeCardapio='" + nomeCardapio + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}