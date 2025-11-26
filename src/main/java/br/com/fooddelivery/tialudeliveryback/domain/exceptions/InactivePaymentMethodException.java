package br.com.foodelivery.tialudeliveryback.domain.exceptions;

public class PaymentMethodNotFoundException extends RuntimeException {

    private final String paymentMethodId;

    public PaymentMethodNotFoundException(String paymentMethodId) {
        super(paymentMethodId);
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }
}