package br.com.fooddelivery.tialudeliveryback.exception;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException() {
        super("Token inválido.");
    }
}