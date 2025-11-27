package br.com.fooddelivery.tialudeliveryback.domain.exceptions;

public class PaymentMethodNotFoundException extends RuntimeException {
    public PaymentMethodNotFoundException(String id) {
        super("Método de pagamento não encontrado: " + id);
    }
}
