package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    @JsonProperty("id_endereco")
    private Long id;

    private String cep;

    @JsonProperty("tipo_logradouro")
    private String tipoLogradouro;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String complemento;

    private String tipo;

    @JsonProperty("padrao_entrega")
    private boolean padraoEntrega;
}
