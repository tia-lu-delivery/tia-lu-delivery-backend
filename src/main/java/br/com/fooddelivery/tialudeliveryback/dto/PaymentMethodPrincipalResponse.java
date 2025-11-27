package br.com.foodelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethodPrincipalResponse {

    @JsonProperty("id_meio_pagamento")
    private String idMeioPagamento;

    private String status;

    private String detalhe;

    private boolean principal;

    public PaymentMethodPrincipalResponse() {}

    public PaymentMethodPrincipalResponse(String idMeioPagamento, String status, String detalhe, boolean principal) {
        this.idMeioPagamento = idMeioPagamento;
        this.status = status;
        this.detalhe = detalhe;
        this.principal = principal;
    }

    public String getIdMeioPagamento() {
        return idMeioPagamento;
    }

    public void setIdMeioPagamento(String idMeioPagamento) {
        this.idMeioPagamento = idMeioPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}