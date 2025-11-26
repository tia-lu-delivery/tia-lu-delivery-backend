package br.com.fooddelivery.tialudeliveryback.dto;

public class ErroResponseDTO {
    private final DetalheErroDTO erro;
    public ErroResponseDTO(DetalheErroDTO erro) {
        this.erro = erro;
    }

    public DetalheErroDTO getErro() { return erro; }
}