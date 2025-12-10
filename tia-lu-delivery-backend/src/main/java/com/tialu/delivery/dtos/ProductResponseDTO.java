package com.tialu.delivery.dtos;

import java.math.BigDecimal;


public class ProductResponseDTO {

    private String idProduto;
    private String mensagem;
    private DadosProdutoResponse dadosProduto;



    public static class DadosProdutoResponse {
        private String nome;
        private BigDecimal precoUnitario;
        private Long categoriaId;


        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public BigDecimal getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
        public Long getCategoriaId() { return categoriaId; }
        public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    }


    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public DadosProdutoResponse getDadosProduto() { return dadosProduto; }
    public void setDadosProduto(DadosProdutoResponse dadosProduto) { this.dadosProduto = dadosProduto; }
}