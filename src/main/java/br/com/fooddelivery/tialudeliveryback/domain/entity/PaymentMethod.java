package br.com.fooddelivery.tialudeliveryback.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_payment_method")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    @Id
    @Column(name = "id_meio_pagamento")
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    private String tipo;

    private String numero;

    private boolean ativo = true;
}
