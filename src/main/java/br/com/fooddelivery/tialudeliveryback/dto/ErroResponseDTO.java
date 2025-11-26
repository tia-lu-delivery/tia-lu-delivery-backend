package br.com.fooddelivery.tialudeliveryback.dto;

// DTO de alto nível para o corpo da resposta de erro
public class ErroResponseDTO {
    private final DetalheErroDTO erro; // CA-002: Nome de campo exigido

    public ErroResponseDTO(DetalheErroDTO erro) {
        this.erro = erro;
    }

    public DetalheErroDTO getErro() { return erro; }
}