package br.com.foodelivery.tialudeliveryback.controller;

import br.com.foodelivery.tialudeliveryback.commands.SetPrincipalPaymentMethodCommand;
import br.com.foodelivery.tialudeliveryback.handlers.SetPrincipalPaymentMethodHandler;
import br.com.foodelivery.tialudeliveryback.dto.PaymentMethodPrincipalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/wallet/payment-method")
public class SetPrincipalPaymentMethodController {

    private final SetPrincipalPaymentMethodHandler handler;

    public SetPrincipalPaymentMethodController(SetPrincipalPaymentMethodHandler handler) {
        this.handler = handler;
    }

    @PatchMapping("/{id}/set-principal")
    public ResponseEntity<PaymentMethodPrincipalResponse> setPrincipal(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable("id") String id
    ) {
        SetPrincipalPaymentMethodCommand command = new SetPrincipalPaymentMethodCommand(userId, id);
        PaymentMethodPrincipalResponse response = handler.handle(command);
        return ResponseEntity.ok(response);
    }
}