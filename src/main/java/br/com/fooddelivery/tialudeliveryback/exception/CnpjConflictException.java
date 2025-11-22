package br.com.fooddelivery.tialudeliveryback.exception;


public class CnpjConflictException extends RuntimeException{
    public CnpjConflictException(String message) {
        super(message);
    }
}
