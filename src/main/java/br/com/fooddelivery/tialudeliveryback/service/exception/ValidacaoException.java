package br.com.fooddelivery.tialudeliveryback.service.exception;

public class ValidacaoException extends RuntimeException {

    private final String campo;

    public ValidacaoException(String campo, String mensagem) {
        super(mensagem);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}
