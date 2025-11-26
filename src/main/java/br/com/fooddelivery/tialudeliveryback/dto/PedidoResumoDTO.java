package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.OffsetDateTime; // ATUALIZADO: Tipo de dado com fuso horário

public class PedidoResumoDTO {
    private String numeroPedido;

    // CA-004: Formato de data/hora exigido no JSON de sucesso
    // Funciona corretamente com OffsetDateTime
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime dataAbertura;

    private String statusPedido;
    private String nomeRestaurante;
    private BigDecimal valorTotal; // BigDecimal para garantir formatação monetária

    // ATUALIZADO: Construtor para receber OffsetDateTime
    public PedidoResumoDTO(String numeroPedido, OffsetDateTime dataAbertura, String statusPedido, String nomeRestaurante, BigDecimal valorTotal) {
        this.numeroPedido = numeroPedido;
        this.dataAbertura = dataAbertura;
        this.statusPedido = statusPedido;
        this.nomeRestaurante = nomeRestaurante;
        this.valorTotal = valorTotal;
    }

    // Getters
    public String getNumeroPedido() { return numeroPedido; }

    // Tipo de retorno alterado para OffsetDateTime
    public OffsetDateTime getDataAbertura() { return dataAbertura; }

    public String getStatusPedido() { return statusPedido; }
    public String getNomeRestaurante() { return nomeRestaurante; }
    public BigDecimal getValorTotal() { return valorTotal; }

    // Setters
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }
    public void setDataAbertura(OffsetDateTime dataAbertura) { this.dataAbertura = dataAbertura; }
    public void setStatusPedido(String statusPedido) { this.statusPedido = statusPedido; }
    public void setNomeRestaurante(String nomeRestaurante) { this.nomeRestaurante = nomeRestaurante; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}