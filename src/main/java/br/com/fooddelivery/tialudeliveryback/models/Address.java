package br.com.fooddelivery.tialudeliveryback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long userId;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "tipo_logradouro", nullable = false)
    private String tipoLogradouro;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "padrao_entrega", nullable = false)
    private boolean padraoEntrega = false;
}
