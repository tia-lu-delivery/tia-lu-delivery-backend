package br.com.fooddelivery.tialudeliveryback.entity;

public enum StatusPagamento {
    APROVADO("Pagamento Aprovado"),
    REJEITADO("Pagamento Rejeitado"),
    ERRO("Erro de Processamento");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }
}