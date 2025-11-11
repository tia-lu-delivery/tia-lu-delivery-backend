package br.com.fooddelivery.tialudeliveryback.exception;

/**
 * Exceção para indicar acesso não autorizado do estabelecimento.
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
