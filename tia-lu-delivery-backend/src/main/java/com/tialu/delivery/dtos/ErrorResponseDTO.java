package com.tialu.delivery.dtos;


public class ErrorResponseDTO {

    private String codigoErro;
    private String mensagem;
    private Object detalhes;


    public ErrorResponseDTO(String codigoErro, String mensagem) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
    }


    public String getCodigoErro() { return codigoErro; }
    public void setCodigoErro(String codigoErro) { this.codigoErro = codigoErro; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public Object getDetalhes() { return detalhes; }
    public void setDetalhes(Object detalhes) { this.detalhes = detalhes; }
}