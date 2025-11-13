package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamento")
@Data
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPagamento;

    @Column(name = "id_pedido", nullable = false)
    private String idPedido;

    @Column(name = "id_meio_pagamento", nullable = false)
    private String idMeioPagamento;

    @Column(name = "id_transacao", nullable = false)
    private String idTransacao;

    @Column(name = "valor_total_cobrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotalCobrado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento;

    @Column(name = "codigo_rejeicao")
    private String codigoRejeicao;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @Column(name = "parcelas", nullable = false)
    private Integer parcelas = 1;

    @Column(name = "detalhes_meio_pagamento")
    private String detalhesMeioPagamento;

    @Column(name = "erro_envio_estabelecimento", nullable = false)
    private Boolean erroEnvioEstabelecimento = false;
}