package br.com.fooddelivery.tialudeliveryback.domain.wallet;

import br.com.fooddelivery.tialudeliveryback.domain.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class UserWallet {

    private final UserId userId;
    private final List<PaymentMethod> methods = new ArrayList<>();

    public UserWallet(UserId userId) {
        this.userId = userId;
    }

    public void addMethod(PaymentMethod method) {
        methods.add(method);
    }

    public void definePrincipal(String paymentMethodId) {

        PaymentMethod target = methods.stream()
                .filter(m -> m.getId().value().equals(paymentMethodId))
                .findFirst()
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));

        if (!target.isActive()) {
            throw new PaymentMethodInactiveException(paymentMethodId);
        }

        // desmarca o atual
        methods.forEach(PaymentMethod::removePrincipal);

        // marca o novo principal
        target.setAsPrincipal();
    }

    public List<PaymentMethod> getMethods() {
        return methods;
    }
}
