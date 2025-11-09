package br.com.fooddelivery.tialudeliveryback.controller;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoResponseDTO;
import br.com.fooddelivery.tialudeliveryback.model.Pedido;
import br.com.fooddelivery.tialudeliveryback.repository.PedidoRepository;
import br.com.fooddelivery.tialudeliveryback.service.PedidoService;
import br.com.fooddelivery.tialudeliveryback.dto.ApiErrorResponse;
import br.com.fooddelivery.tialudeliveryback.exception.ValidacaoPedidoException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@Valid @RequestBody PedidoRequestDTO request) {

        PedidoResponseDTO response = pedidoService.registrarPedido(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        return ResponseEntity.ok(pedido);
    }

    @ExceptionHandler(ValidacaoPedidoException.class)
    public ResponseEntity<ApiErrorResponse> handleValidacaoPedidoException(ValidacaoPedidoException ex) {

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "ERRO_VALIDACAO_NEGOCIO",
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}