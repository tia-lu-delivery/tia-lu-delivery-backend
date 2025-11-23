package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodDeleteResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/wallet/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @DeleteMapping("/{id_meio_pagamento}")
    public ResponseEntity<PaymentMethodDeleteResponseDTO> deletePaymentMethod(
            @PathVariable("id_meio_pagamento") String paymentMethodId,
            @RequestHeader(value = "X-User-Id", required = true) String userId) {
        
        String deletedId = paymentMethodService.deletePaymentMethod(paymentMethodId, userId);
        
        return ResponseEntity.ok(PaymentMethodDeleteResponseDTO.success(deletedId));
    }
}
