package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.UpdatePaymentMethodRequest;
import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.PaymentMethodService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/wallet/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponseDTO> updatePaymentMethod(
            @PathVariable String id,
            @Valid @RequestBody UpdatePaymentMethodRequest request
    ) {
        PaymentMethodResponseDTO dto = service.updatePaymentMethod(id, request);
        return ResponseEntity.ok(dto);
    }
}
