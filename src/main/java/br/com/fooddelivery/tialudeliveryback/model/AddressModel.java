package br.com.fooddelivery.tialudeliveryback.model;

public class AddressModel {

    private String cep;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String tipo;
    private Boolean padraoEntrega;

    public AddressModel() {
    }

    public AddressModel(String cep, String tipoLogradouro, String logradouro, String numero, String bairro, String cidade, String estado, String complemento, String tipo, Boolean padraoEntrega) {
        this.cep = cep;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.tipo = tipo;
        this.padraoEntrega = padraoEntrega;
    }

    // Getters e Setters
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getPadraoEntrega() {
        return padraoEntrega;
    }

    public void setPadraoEntrega(Boolean padraoEntrega) {
        this.padraoEntrega = padraoEntrega;
    }
}