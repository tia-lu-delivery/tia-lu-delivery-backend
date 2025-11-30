package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.ErroResponse;
import br.com.fooddelivery.tialudeliveryback.dto.PedidoResponseDTO;
import br.com.fooddelivery.tialudeliveryback.security.AuthValidator;
import br.com.fooddelivery.tialudeliveryback.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {

    private final PedidoService pedidoService;
    private final AuthValidator authValidator;

    public PedidoController(PedidoService pedidoService, AuthValidator authValidator) {
        this.pedidoService = pedidoService;
        this.authValidator = authValidator;
    }

    @GetMapping("/{numero_pedido}")
    public ResponseEntity<?> getPedidoPorNumero(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable("numero_pedido") String numero_pedido) {

        // 401 → Token ausente
        if (authValidator.isTokenAusente(authorizationHeader)) {
            return ResponseEntity.status(401)
                    .body(new ErroResponse(
                            "NAO_AUTORIZADO",
                            "Acesso negado. Token de autenticação ausente ou inválido."
                    ));
        }

        // 403 → Token inválido
        if (authValidator.isTokenInvalido(authorizationHeader)) {
            return ResponseEntity.status(403)
                    .body(new ErroResponse(
                            "TOKEN_INVALIDO",
                            "Acesso negado. Token fornecido é inválido."
                    ));
        }

        // Token OK — busca pedido
        PedidoResponseDTO pedido = pedidoService.buscarPorNumero(numero_pedido);

        if (pedido == null) {
            return ResponseEntity.status(404)
                    .body(new ErroResponse(
                            "PEDIDO_NAO_ENCONTRADO",
                            "O pedido com o número '" + numero_pedido + "' não existe."
                    ));
        }

        return ResponseEntity.ok(pedido);
    }
}