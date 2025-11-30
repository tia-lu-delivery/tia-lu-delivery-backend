package br.com.fooddelivery.tialudeliveryback.dto;

public class PaymentMethodDTO {

    private String idMeioPagamento;
    private String bandeiraUrl;
    private String ultimosDigitos;
    private String tipoCartao;
    private String nomeTitular;
    private String validade;

    // Getters e Setters

    public String getIdMeioPagamento() {
        return idMeioPagamento;
    }

    public void setIdMeioPagamento(String idMeioPagamento) {
        this.idMeioPagamento = idMeioPagamento;
    }

    public String getBandeiraUrl() {
        return bandeiraUrl;
    }

    public void setBandeiraUrl(String bandeiraUrl) {
        this.bandeiraUrl = bandeiraUrl;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        this.ultimosDigitos = ultimosDigitos;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
