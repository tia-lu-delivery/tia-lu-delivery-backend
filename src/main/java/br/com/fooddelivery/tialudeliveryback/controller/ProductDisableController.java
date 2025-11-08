package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.services.ProductDisableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controlador responsável por lidar com requisições relacionadas à inativação de produtos
@RestController
@RequestMapping("/api/v1/merchant/{id_estabelecimento}/products")
public class ProductDisableController {

    private final ProductDisableService productDisableService;

    public ProductDisableController(ProductDisableService productDisableService){
        this.productDisableService = productDisableService;
    }

    /**z
     * Endpoint para inativar desabilitar um produto específico de um estabelecimento.
     *
     * @param idEstabelecimento ID do estabelecimento
     * @param idProduto ID do produto a ser inativado
     * @return HTTP 200 se a operação for bem-sucedida
     */
    @PutMapping("/{id_produto}/disable")
    public ResponseEntity<Void> disableProduct(
            @PathVariable("id_estabelecimento") Long idEstabelecimento,
            @PathVariable("id_produto") Long idProduto ){

        // Chama o service e armazena o DTO que ele retorna
        ProductDisableResponseDTO responseDTO = productDisableService.disableProduct(idEstabelecimento, idProduto);

        // Retorna o status 200 (OK) caso ocorra tudo certo
        return ResponseEntity.ok(productDisableService);
    }


}
