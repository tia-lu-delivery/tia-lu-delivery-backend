package br.com.fooddelivery/tialudeliveryback/domain/exceptions;

public class PaymentMethodInactiveException extends RuntimeException {
    public PaymentMethodInactiveException(String id) {
        super("Método de pagamento está inativo: " + id);
    }
}
