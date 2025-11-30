package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodListResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/{idUsuario}/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<?> listarMeiosPagamento(
            @PathVariable("idUsuario") Long idUsuario
    ) {
        PaymentMethodListResponseDTO response = paymentMethodService.listarMeiosPagamento(idUsuario);
        return ResponseEntity.ok(response);
    }
}
