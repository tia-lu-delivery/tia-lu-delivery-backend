package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.web.bind.annotation.*;

// Indica que esta classe é um controller REST (retorna JSON)
@RestController

// Define o prefixo da rota (todas as rotas aqui começam com /api/v1/orders)
@RequestMapping("/api/v1/orders")
public class PedidoController {

    // Endpoint: GET /api/v1/orders/{numero_pedido}
    @GetMapping("/{numero_pedido}")
    public String getPedidoPorNumero(@PathVariable String numero_pedido) {
        return "Detalhes do pedido número: " + numero_pedido;
    }
}

