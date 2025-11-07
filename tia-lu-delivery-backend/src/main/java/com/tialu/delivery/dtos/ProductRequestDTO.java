
package com.tialu.delivery.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;


public class ProductRequestDTO {


    @NotEmpty(message = "O nome do produto é obrigatório.")
    private String nome;


    @NotNull(message = "O preço unitário é obrigatório.")
    @PositiveOrZero(message = "O preço unitário deve ser um valor numérico positivo.")
    private BigDecimal precoUnitario;


    @NotEmpty(message = "A descrição é obrigatória.")
    private String descricao;


    @NotNull(message = "A quantidade em estoque é obrigatória.")
    @PositiveOrZero(message = "A quantidadeEstoque deve ser um número inteiro positivo.")
    private Integer quantidadeEstoque;

    private String imagemUrl;
    private boolean disponivel = true;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}