package br.com.fooddelivery.tialudeliveryback.dto;

// DTO para a estrutura aninhada do erro ("codigo" e "detalhe")
public class DetalheErroDTO {
    private final String codigo;
    private final String detalhe;

    public DetalheErroDTO(String codigo, String detalhe) {
        this.codigo = codigo;
        this.detalhe = detalhe;
    }

    public String getCodigo() { return codigo; }
    public String getDetalhe() { return detalhe; }
}