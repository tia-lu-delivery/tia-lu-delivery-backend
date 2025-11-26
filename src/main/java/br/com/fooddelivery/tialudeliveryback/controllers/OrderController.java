package br.com.fooddelivery.tialudeliveryback.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/user/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarPedidosComFiltro(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String restaurante,
            @RequestParam(required = false) Double valorMin,
            @RequestParam(required = false) Double valorMax
    ) {

        // ----------------- DADOS FAKE (pois não existem entity/repository ainda) -----------------
        List<Map<String, Object>> pedidos = gerarPedidosFake();

        // ----------------- APLICAÇÃO DE FILTROS -----------------
        if (status != null && !status.isEmpty()) {
            pedidos.removeIf(p -> !p.get("status_pedido").toString().equalsIgnoreCase(status));
        }

        if (restaurante != null && !restaurante.isEmpty()) {
            pedidos.removeIf(p -> !p.get("nome_restaurante").toString().toLowerCase()
                    .contains(restaurante.toLowerCase()));
        }

        if (valorMin != null) {
            pedidos.removeIf(p -> ((Double) p.get("valor_total")) < valorMin);
        }

        if (valorMax != null) {
            pedidos.removeIf(p -> ((Double) p.get("valor_total")) > valorMax);
        }

        // ----------------- ORDENA -----------------
        pedidos.sort((a, b) ->
                ((OffsetDateTime) b.get("data_abertura"))
                        .compareTo((OffsetDateTime) a.get("data_abertura"))
        );

        // ----------------- MONTA RESPOSTA (sem paginação real, pois é a task #137) -----------------
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("total_filtrado", pedidos.size());
        resposta.put("pedidos", pedidos);

        return ResponseEntity.ok(resposta);
    }


    // -------------------- GERADOR DE DADOS FAKE --------------------
    private List<Map<String, Object>> gerarPedidosFake() {
        List<Map<String, Object>> lista = new ArrayList<>();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("numero_pedido", "202511130099");
        p1.put("data_abertura", OffsetDateTime.parse("2025-11-13T16:45:00-03:00"));
        p1.put("status_pedido", "Em Preparação");
        p1.put("nome_restaurante", "Burger Mania");
        p1.put("valor_total", 75.30);
        lista.add(p1);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("numero_pedido", "202511130088");
        p2.put("data_abertura", OffsetDateTime.parse("2025-11-13T12:15:00-03:00"));
        p2.put("status_pedido", "Entregue");
        p2.put("nome_restaurante", "Cantina Italiana");
        p2.put("valor_total", 120.00);
        lista.add(p2);

        Map<String, Object> p3 = new HashMap<>();
        p3.put("numero_pedido", "202511120055");
        p3.put("data_abertura", OffsetDateTime.parse("2025-11-12T18:20:00-03:00"));
        p3.put("status_pedido", "Entregue");
        p3.put("nome_restaurante", "Sushi House");
        p3.put("valor_total", 98.90);
        lista.add(p3);

        return lista;
    }

}
