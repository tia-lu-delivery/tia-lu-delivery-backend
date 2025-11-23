package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.domain.repository.PaymentMethodRepository;
import br.com.fooddelivery.tialudeliveryback.exception.PaymentMethodNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public String deletePaymentMethod(String paymentMethodId, String userId) {
        PaymentMethod paymentMethod = paymentMethodRepository
                .findByIdAndUserId(paymentMethodId, userId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(
                        String.format("O meio de pagamento com ID '%s' não foi encontrado ou não pertence a este usuário.", paymentMethodId)
                ));

        // Soft delete
        paymentMethod.setAtivo(false);
        paymentMethod.setInativadoEm(LocalDateTime.now());
        paymentMethodRepository.save(paymentMethod);

        return paymentMethod.getId();
    }
}
