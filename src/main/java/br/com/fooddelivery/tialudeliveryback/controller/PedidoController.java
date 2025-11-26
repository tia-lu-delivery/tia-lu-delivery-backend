package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.DetalheErroDTO;
import br.com.fooddelivery.tialudeliveryback.dto.ErroResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dto.ListaPedidosResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/orders")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Object> listarPedidosUsuario(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (token == null || !token.startsWith("Bearer ")) {

            DetalheErroDTO detalhe = new DetalheErroDTO(
                    "NAO_AUTORIZADO",
                    "Token de autenticação ausente ou inválido. Não foi possível carregar o histórico."
            );

            ErroResponseDTO erroResponse = new ErroResponseDTO(detalhe);

            return ResponseEntity.status(401).body(erroResponse);
        }

        Long userId = 123L;

        ListaPedidosResponseDTO response = pedidoService.listarPedidosUsuario(userId, page, size);

        return ResponseEntity.ok(response);
    }
}