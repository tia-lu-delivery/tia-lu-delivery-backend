package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/{idUsuario}/payment-methods")
public class PaymentMethodController {

    @GetMapping
    public ResponseEntity<String> listarMeiosPagamento(
            @PathVariable("idUsuario") Long idUsuario
    ) {
        return ResponseEntity.ok("Endpoint de listagem de meios de pagamento funcionando! Usuário: " + idUsuario);
    }
}
