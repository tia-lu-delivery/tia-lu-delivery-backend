package br.com.fooddelivery.tialudeliveryback.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
