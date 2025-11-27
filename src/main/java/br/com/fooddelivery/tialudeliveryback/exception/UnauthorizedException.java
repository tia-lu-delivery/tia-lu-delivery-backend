package br.com.fooddelivery.tialudeliveryback.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Token de autenticação ausente ou inválido.");
    }

    public UnauthorizedException(String mensagem) {
        super(mensagem);
    }
}
