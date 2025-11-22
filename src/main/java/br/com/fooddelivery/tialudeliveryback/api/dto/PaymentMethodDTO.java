package br.com.fooddelivery.tialudeliveryback.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data; // Se estiver usando Lombok, senão crie Getters e Setters

@Data // Gera getters e setters automaticamente
public class PaymentMethodDTO {

    @JsonProperty("id_meio_pagamento")
    private String idMeioPagamento;

    private String bandeira;

    @JsonProperty("bandeira_url")
    private String bandeiraUrl;

    @JsonProperty("ultimos_digitos")
    private String ultimosDigitos;

    @JsonProperty("nome_titular")
    private String nomeTitular;

    @JsonProperty("validade_mes")
    private Integer validadeMes;

    @JsonProperty("validade_ano")
    private Integer validadeAno;

    @JsonProperty("tipo_cartao")
    private String tipoCartao;

    @JsonProperty("status_ativo")
    private Boolean statusAtivo;
}