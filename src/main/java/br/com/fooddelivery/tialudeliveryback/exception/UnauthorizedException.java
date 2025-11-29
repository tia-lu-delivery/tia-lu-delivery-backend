package br.com.fooddelivery.tialudeliveryback.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String detalhe) {
        super(detalhe);
    }
}
