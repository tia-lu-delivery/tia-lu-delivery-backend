package com.fooddelivery.tialudeliveryback.dto;

import java.time.LocalDateTime;

public class CardapioResponse {
    private String idCardapio;
    private String mensagem;
    private DadosCardapio dadosCardapio;
    
    public CardapioResponse() {}
    
    public CardapioResponse(String idCardapio, String mensagem, DadosCardapio dadosCardapio) {
        this.idCardapio = idCardapio;
        this.mensagem = mensagem;
        this.dadosCardapio = dadosCardapio;
    }
    
    public String getIdCardapio() { return idCardapio; }
    public void setIdCardapio(String idCardapio) { this.idCardapio = idCardapio; }
    
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    
    public DadosCardapio getDadosCardapio() { return dadosCardapio; }
    public void setDadosCardapio(DadosCardapio dadosCardapio) { this.dadosCardapio = dadosCardapio; }
    
    public static class DadosCardapio {
        private String nomeCardapio;
        private String status;
        private String descricao;
        private LocalDateTime dataCriacao;
        
        public DadosCardapio() {}
        
        public DadosCardapio(String nomeCardapio, String status, String descricao, LocalDateTime dataCriacao) {
            this.nomeCardapio = nomeCardapio;
            this.status = status;
            this.descricao = descricao;
            this.dataCriacao = dataCriacao;
        }
   
        public String getNomeCardapio() { return nomeCardapio; }
        public void setNomeCardapio(String nomeCardapio) { this.nomeCardapio = nomeCardapio; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }
        
        public LocalDateTime getDataCriacao() { return dataCriacao; }
        public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    }
}