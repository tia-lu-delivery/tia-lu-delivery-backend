package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.ProductResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable Long id) {
        try {
            ProductResponseDTO produto = service.getProductDetails(id);
            return ResponseEntity.ok(produto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(Map.of(
                "erro", Map.of(
                    "codigo", "PRODUTO_NAO_ENCONTRADO",
                    "detalhe", e.getMessage()
                )
            ));
        }
    }
}


