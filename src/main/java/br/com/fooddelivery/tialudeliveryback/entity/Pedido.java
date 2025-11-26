package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pedido")
    private String numeroPedido;

    @Column(name = "data_abertura")
    private OffsetDateTime dataAbertura;

    @Column(name = "status_pedido")
    private String statusPedido;

    @Column(name = "nome_restaurante")
    private String nomeRestaurante;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public Pedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    public OffsetDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(OffsetDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getStatusPedido() { return statusPedido; }
    public void setStatusPedido(String statusPedido) { this.statusPedido = statusPedido; }

    public String getNomeRestaurante() { return nomeRestaurante; }
    public void setNomeRestaurante(String nomeRestaurante) { this.nomeRestaurante = nomeRestaurante; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}