package br.com.fooddelivery.tialudeliveryback.exception;

public class PaymentMethodNotFoundException extends RuntimeException {
    
    public PaymentMethodNotFoundException(String message) {
        super(message);
    }
}
