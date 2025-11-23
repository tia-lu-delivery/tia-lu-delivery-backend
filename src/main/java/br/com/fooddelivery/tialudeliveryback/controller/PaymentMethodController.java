package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodDeleteResponse;
import br.com.fooddelivery.tialudeliveryback.exception.UnauthorizedException;
import br.com.fooddelivery.tialudeliveryback.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/wallet/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentMethodDeleteResponse> deletePaymentMethod(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {

        if (userId == null || userId.isEmpty()) {
            throw new UnauthorizedException("Token de autenticação ausente ou inválido.");
        }

        paymentMethodService.deletePaymentMethod(id, userId);

        PaymentMethodDeleteResponse response = new PaymentMethodDeleteResponse(
                "sucesso",
                "Meio de pagamento removido da carteira (status inativado).",
                id
        );

        return ResponseEntity.ok(response);
    }
}