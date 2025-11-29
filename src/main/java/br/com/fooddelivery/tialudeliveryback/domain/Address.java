package br.com.fooddelivery.tialudeliveryback.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId; // Provisório: representando dono do endereço

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(name = "tipo_logradouro", nullable = false)
    private String tipoLogradouro;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    private String complemento;

    @Column(nullable = false)
    private String tipo; // Ex: Residencial, Comercial

    @Column(name = "padrao_entrega", nullable = false)
    private boolean padraoEntrega;
}
