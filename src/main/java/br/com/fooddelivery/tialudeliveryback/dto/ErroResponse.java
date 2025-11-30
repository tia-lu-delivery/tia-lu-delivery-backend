package br.com.fooddelivery.tialudeliveryback.dto;

public class ErroResponse {

    private String codigo;
    private String detalhe;

    public ErroResponse(String codigo, String detalhe) {
        this.codigo = codigo;
        this.detalhe = detalhe;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetalhe() {
        return detalhe;
    }
}
