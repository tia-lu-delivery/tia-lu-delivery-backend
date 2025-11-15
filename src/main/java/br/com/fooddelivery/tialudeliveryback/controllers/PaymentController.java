package br.com.fooddelivery.tialudeliveryback.controllers;

import br.com.fooddelivery.tialudeliveryback.service.PagamentoService;
import br.com.fooddelivery.tialudeliveryback.dto.PagamentoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PagamentoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class PaymentController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/{idPedido}/payments")
    public ResponseEntity<PagamentoResponseDTO> efetuarPagamento(
            @PathVariable String idPedido,
            @Valid @RequestBody PagamentoRequestDTO requestDTO) {

        PagamentoResponseDTO response = pagamentoService.efetuarPagamento(idPedido, requestDTO);
        return ResponseEntity.ok(response);
    }
}