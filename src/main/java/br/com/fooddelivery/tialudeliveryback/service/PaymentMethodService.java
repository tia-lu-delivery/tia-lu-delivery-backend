package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.model.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PaymentMethod setAsPrincipal(String paymentMethodId, String userId) {
        Optional<PaymentMethod> maybe = repository.findByIdAndUserId(paymentMethodId, userId);
        if (maybe.isEmpty()) {
            throw new NotFoundException("MEIO_PAGAMENTO_NAO_ENCONTRADO", "O meio de pagamento com ID '" + paymentMethodId + "' não foi encontrado ou não pertence a este usuário.");
        }
        PaymentMethod pm = maybe.get();
        if (!pm.isActive()) {
            throw new BadRequestException("MEIO_PAGAMENTO_INATIVO", "Não é possível definir um meio de pagamento inativo como principal.");
        }

        // Unset other principals
        repository.unsetPrincipalForOthers(userId, paymentMethodId);

        // Set requested as principal
        pm.setPrincipal(true);
        repository.save(pm);

        return pm;
    }

    // Custom exception types for clarity
    public static class NotFoundException extends RuntimeException {
        public final String code;
        public NotFoundException(String code, String message) {
            super(message);
            this.code = code;
        }
    }

    public static class BadRequestException extends RuntimeException {
        public final String code;
        public BadRequestException(String code, String message) {
            super(message);
            this.code = code;
        }
    }
}

