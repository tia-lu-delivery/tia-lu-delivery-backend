package br.com.fooddelivery/tialudeliveryback/domain/exceptions;

public class UnauthorizedPaymentMethodException extends RuntimeException {
    public UnauthorizedPaymentMethodException() {
        super("Usuário não tem permissão para alterar este método de pagamento.");
    }
}
