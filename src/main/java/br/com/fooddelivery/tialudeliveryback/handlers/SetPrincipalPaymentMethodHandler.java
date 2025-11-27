package br.com.foodelivery.tialudeliveryback.handlers;

import br.com.foodelivery.tialudeliveryback.commands.SetPrincipalPaymentMethodCommand;
import br.com.foodelivery.tialudeliveryback.domain.PaymentMethod;
import br.com.foodelivery.tialudeliveryback.domain.exceptions.InactivePaymentMethodException;
import br.com.foodelivery.tialudeliveryback.domain.exceptions.PaymentMethodNotFoundException;
import br.com.foodelivery.tialudeliveryback.dto.PaymentMethodPrincipalResponse;
import br.com.foodelivery.tialudeliveryback.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SetPrincipalPaymentMethodHandler {

    private final PaymentMethodRepository repository;

    public SetPrincipalPaymentMethodHandler(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PaymentMethodPrincipalResponse handle(SetPrincipalPaymentMethodCommand command) {
        String paymentMethodId = command.paymentMethodId();
        Long userId = command.userId();
        PaymentMethod pm = repository.findByIdAndUserId(paymentMethodId, userId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
        if (!pm.isActive()) {
            throw new InactivePaymentMethodException();
        }
        repository.findByUserIdAndPrincipalTrue(userId).ifPresent(existing -> {
            if (!existing.getId().equals(pm.getId())) {
                existing.setPrincipal(false);
                repository.save(existing);
            }
        });
        pm.setPrincipal(true);
        repository.save(pm);
        return new PaymentMethodPrincipalResponse(pm.getId(), "sucesso", "Meio de pagamento definido como principal com sucesso.", true);
    }
}