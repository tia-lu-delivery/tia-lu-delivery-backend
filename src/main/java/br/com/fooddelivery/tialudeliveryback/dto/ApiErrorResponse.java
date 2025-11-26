package br.com.fooddelivery.tialudeliveryback.dto;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private int status;
    private String codigoErro;
    private String mensagem;
    private LocalDateTime timestamp;

    public ApiErrorResponse(int status, String codigoErro, String mensagem) {
        this.status = status;
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}