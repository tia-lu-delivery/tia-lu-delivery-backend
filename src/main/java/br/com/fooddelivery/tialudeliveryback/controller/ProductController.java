package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;

/**
 * Controller responsável por operações de disponibilidade de produtos no cardápio do estabelecimento.
 *
 * Endpoint da história DWOO-0018: habilitar produto para venda (Lisboa).
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Marca um produto como disponível para venda.
     * Método: PUT
     * Rota: /api/v1/merchant/{id_estabelecimento}/products/{id_produto}/enable
     * Body: não requerido
     *
     * Respostas:
     * - 200 OK: produto ativado (ProductEnableResponse)
     * - 404 Not Found: produto/estabelecimento não encontrado (envelope { erro: { codigo, detalhe } })
     * - 401 Unauthorized: quando a segurança estiver habilitada (envelope { erro: { codigo, detalhe } })
     */
    @PutMapping(value = "/merchant/{id_estabelecimento}/products/{id_produto}/enable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> enableProduct(
        @PathVariable("id_estabelecimento") String merchantId,
        @PathVariable("id_produto") String productId) {

        ProductEnableResponse result = productService.enableProduct(merchantId, productId);
        return ResponseEntity.ok(result);
    }
}
