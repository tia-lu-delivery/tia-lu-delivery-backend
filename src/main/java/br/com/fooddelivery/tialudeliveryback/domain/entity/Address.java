package br.com.fooddelivery.tialudeliveryback.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(nullable = false)
    private String userId;

    private String cep;

    @Column(name = "tipo_logradouro")
    private String tipoLogradouro;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String complemento;

    private String tipo; // Comercial, Residencial

    @Column(name = "padrao_entrega")
    private boolean padraoEntrega;
}
