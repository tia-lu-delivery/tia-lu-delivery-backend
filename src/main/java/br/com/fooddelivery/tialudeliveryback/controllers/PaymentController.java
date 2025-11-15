package br.com.fooddelivery.tialudeliveryback.controllers;

import br.com.fooddelivery.tialudeliveryback.dtos.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{idUsuario}/payment-methods")
    public ResponseEntity<PaymentResponseDTO> addCard(
            @PathVariable Long idUsuario,
            @Valid @RequestBody PaymentRequestDTO paymentRequest) {

        PaymentResponseDTO response = paymentService.addCard(idUsuario, paymentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}