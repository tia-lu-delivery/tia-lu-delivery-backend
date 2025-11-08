package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.ProductResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/merchant")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id_restaurante}/products/{id_produto}")
    public ResponseEntity<?> getProductDetails(
            @PathVariable String id_restaurante,
            @PathVariable String id_produto) {
        try {
            ProductResponseDTO produto = service.getProductDetails(id_restaurante, id_produto);
            return ResponseEntity.ok(produto);

        } catch (RuntimeException e) {
            if (e.getMessage().contains("restaurante")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "erro", Map.of(
                        "codigo", "RESTAURANTE_NAO_ENCONTRADO",
                        "detalhe", e.getMessage()
                    )
                ));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "erro", Map.of(
                    "codigo", "PRODUTO_NAO_ENCONTRADO",
                    "detalhe", e.getMessage()
                )
            ));
        }
    }
}

