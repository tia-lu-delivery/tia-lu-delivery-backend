package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ItemRequestDTO {

    @NotNull(message = "O ID do produto não pode ser nulo.")
    private Long idProduto;

    @NotNull(message = "A quantidade não pode ser nula.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    private Integer quantidade;

    private BigDecimal precoUnitarioMomentoCompra;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitarioMomentoCompra() {
        return precoUnitarioMomentoCompra;
    }

    public void setPrecoUnitarioMomentoCompra(BigDecimal precoUnitarioMomentoCompra) {
        this.precoUnitarioMomentoCompra = precoUnitarioMomentoCompra;
    }
}