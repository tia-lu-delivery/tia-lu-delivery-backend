package com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class ErrorResponse {
    private String codigoErro;
    private String mensagem;
    private String campo;
    private List<DetalheErro> detalhes;
    
    public ErrorResponse() {}
    
    public ErrorResponse(String codigoErro, String mensagem) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
    }
    
    public ErrorResponse(String codigoErro, String mensagem, String campo) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
        this.campo = campo;
    }
    
    public String getCodigoErro() { return codigoErro; }
    public void setCodigoErro(String codigoErro) { this.codigoErro = codigoErro; }
    
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    
    public String getCampo() { return campo; }
    public void setCampo(String campo) { this.campo = campo; }
    
    public List<DetalheErro> getDetalhes() { return detalhes; }
    public void setDetalhes(List<DetalheErro> detalhes) { this.detalhes = detalhes; }
    
    public static class DetalheErro {
        private String campo;
        private String erro;
        
        public DetalheErro() {}
        
        public DetalheErro(String campo, String erro) {
            this.campo = campo;
            this.erro = erro;
        }
        
        public String getCampo() { return campo; }
        public void setCampo(String campo) { this.campo = campo; }
        
        public String getErro() { return erro; }
        public void setErro(String erro) { this.erro = erro; }
    }
}