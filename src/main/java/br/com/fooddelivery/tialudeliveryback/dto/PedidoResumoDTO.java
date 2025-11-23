package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResumoDTO {

    @JsonProperty("numero_pedido")
    private String numeroPedido;

    @JsonProperty("data_abertura")
    private LocalDateTime dataAbertura;

    @JsonProperty("status_pedido")
    private String statusPedido;

    @JsonProperty("nome_restaurante")
    private String nomeRestaurante;

    @JsonProperty("valor_total")
    private BigDecimal valorTotal;

    public PedidoResumoDTO() {}

    public PedidoResumoDTO(String numeroPedido, LocalDateTime dataAbertura, String statusPedido, String nomeRestaurante, BigDecimal valorTotal) {
        this.numeroPedido = numeroPedido;
        this.dataAbertura = dataAbertura;
        this.statusPedido = statusPedido;
        this.nomeRestaurante = nomeRestaurante;
        this.valorTotal = valorTotal;
    }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getStatusPedido() { return statusPedido; }
    public void setStatusPedido(String statusPedido) { this.statusPedido = statusPedido; }

    public String getNomeRestaurante() { return nomeRestaurante; }
    public void setNomeRestaurante(String nomeRestaurante) { this.nomeRestaurante = nomeRestaurante; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}