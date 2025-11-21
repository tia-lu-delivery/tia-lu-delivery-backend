package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.UpdatePaymentMethodRequest;
import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponse;
import br.com.fooddelivery.tialudeliveryback.service.PaymentMethodService;
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
    public ResponseEntity<PaymentMethodResponse> update(
            @PathVariable Long id,
            @RequestBody UpdatePaymentMethodRequest request
    ) {
        return ResponseEntity.ok(service.updatePaymentMethod(id, request));
    }
}
