package br.com.fooddelivery.tialudeliveryback.exceptions;

public class DuplicateCardException extends RuntimeException {
    private String acaoSugerida;

    public DuplicateCardException(String message, String acaoSugerida) {
        super(message);
        this.acaoSugerida = acaoSugerida;
    }

    public String getAcaoSugerida() {
        return acaoSugerida;
    }
}