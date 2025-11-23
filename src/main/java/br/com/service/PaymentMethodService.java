package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.domain.repository.PaymentMethodRepository;
import br.com.fooddelivery.tialudeliveryback.exception.PaymentMethodNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public void deletePaymentMethod(String id, String userId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(
                        "O meio de pagamento com ID '" + id + "' não foi encontrado ou não pertence a este usuário."));
        
        paymentMethod.setAtivo(false);
        paymentMethodRepository.save(paymentMethod);
    }
}