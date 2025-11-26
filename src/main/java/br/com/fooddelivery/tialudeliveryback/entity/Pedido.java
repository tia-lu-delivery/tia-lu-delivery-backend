package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // Importado para melhor precisão monetária
import java.time.OffsetDateTime; // ATUALIZADO: Tipo de dado com fuso horário

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pedido")
    private String numeroPedido;

    // ATUALIZADO: Usando OffsetDateTime para suportar o formato com offset no JSON
    @Column(name = "data_abertura")
    private OffsetDateTime dataAbertura;

    @Column(name = "status_pedido")
    private String statusPedido;

    @Column(name = "nome_restaurante")
    private String nomeRestaurante;

    // ATUALIZADO: Usando BigDecimal para garantir precisão monetária
    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "usuario_id")
    private Long usuarioId;

    // Construtores, Getters e Setters
    public Pedido() {}

    // Getters e Setters ATUALIZADOS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    // Tipo de retorno alterado para OffsetDateTime
    public OffsetDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(OffsetDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getStatusPedido() { return statusPedido; }
    public void setStatusPedido(String statusPedido) { this.statusPedido = statusPedido; }

    public String getNomeRestaurante() { return nomeRestaurante; }
    public void setNomeRestaurante(String nomeRestaurante) { this.nomeRestaurante = nomeRestaurante; }

    // Tipo de retorno alterado para BigDecimal
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}