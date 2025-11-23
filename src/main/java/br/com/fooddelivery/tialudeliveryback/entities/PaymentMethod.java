package br.com.fooddelivery.tialudeliveryback.entities;

import br.com.fooddelivery.tialudeliveryback.dtos.CardType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_meio_pagamento", unique = true)
    private String idMeioPagamento;

    @Column(name = "id_usuario", nullable = false)
    private String idUsuario;

    @Column(name = "numero_cartao_criptografado", nullable = false, length = 500)
    private String numeroCartaoCriptografado;

    @Column(name = "validade_mes", nullable = false)
    private Integer validadeMes;

    @Column(name = "validade_ano", nullable = false)
    private Integer validadeAno;

    @Column(name = "cvv_criptografado", nullable = false, length = 500)
    private String cvvCriptografado;

    @Column(name = "nome_titular", nullable = false, length = 100)
    private String nomeTitular;

    @Column(name = "cpf_titular", nullable = false, length = 11)
    private String cpfTitular;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cartao", nullable = false)
    private CardType tipoCartao;

    @Column(name = "bandeira", nullable = false, length = 20)
    private String bandeira;

    @Column(name = "ultimos_digitos", nullable = false, length = 4)
    private String ultimosDigitos;

    @PrePersist
    private void generateIdMeioPagamento() {
        if (this.idMeioPagamento == null) {
            this.idMeioPagamento = "mpid" + System.currentTimeMillis();
        }
    }
}