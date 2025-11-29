package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.ProductResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exception.RestaurantNotFoundException;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/merchant/{id_restaurante}/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<?> getProductDetails(
            @PathVariable("id_restaurante") Long idRestaurante,
            @PathVariable("id_produto") Long idProduto) {

        try {
            ProductResponseDTO dto = service.getProductDetails(idRestaurante, idProduto);
            return ResponseEntity.ok(dto);

        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", Map.of(
                            "codigo", "RESTAURANTE_NAO_ENCONTRADO",
                            "detalhe", e.getMessage()
                    )));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", Map.of(
                            "codigo", "PRODUTO_NAO_ENCONTRADO",
                            "detalhe", e.getMessage()
                    )));
        }
    }

    @GetMapping
    public ResponseEntity<?> listProducts(
            @PathVariable("id_restaurante") Long idRestaurante) {

        try {
            List<ProductResponseDTO> lista = service.listProducts(idRestaurante);
            return ResponseEntity.ok(lista);

        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", Map.of(
                            "codigo", "RESTAURANTE_NAO_ENCONTRADO",
                            "detalhe", e.getMessage()
                    )));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", Map.of(
                            "codigo", "NAO_ENCONTRADO",
                            "detalhe", e.getMessage()
                    )));
        }
    }
}
