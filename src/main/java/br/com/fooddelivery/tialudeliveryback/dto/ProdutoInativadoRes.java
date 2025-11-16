package br.com.fooddelivery.tialudeliveryback.dto;

public class ProdutoInativadoRes {
    
    // Dados do produto inativado 
    private String id_produto;
    private String status;
    private String detalhe;
    private boolean disponivel;

    // Construtor 
    public ProdutoInativadoRes(String id_produto, String status, String detalhe, boolean disponivel) {
        this.id_produto = id_produto;
        this.status = status;
        this.detalhe = detalhe;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public String getId_produto() {
        return id_produto;
    }

    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
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

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}

