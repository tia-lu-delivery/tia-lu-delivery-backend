package br.com.fooddelivery.tialudeliveryback.api.dto;

public class ErrorResponseDTO {
    private String codigo;
    private String detalhe;

    public ErrorResponseDTO(String codigo, String detalhe) {
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
