package br.com.fooddelivery.tialudeliveryback.Controllers;

import br.com.fooddelivery.tialudeliveryback.Service.ProductDisableService;
import br.com.fooddelivery.tialudeliveryback.dto.ProdutoInativadoRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/merchant/{id_estabelecimento}/products")
public class ProductDisableController {

    private final ProductDisableService productDisableService;

    public ProductDisableController(ProductDisableService productDisableService){
        this.productDisableService = productDisableService;
    }

    /**
     * Endpoint para inativar um produto, usa try-catch para garantir o mapeamento direto
     * dos retornos 200, 404 e 401
     */
    @PutMapping("/{id_produto}/disable")
    // Permite o retorno de DTO (200) ou Map de Erro (404/401/500)
    public ResponseEntity<?> disableProduct(
            @PathVariable("id_estabelecimento") Long idEstabelecimento,
            @PathVariable("id_produto") Long idProduto ){

        try {
            // Chama o Service, o Service deve retornar o DTO em caso de sucesso
            ProdutoInativadoRes responseDTO = productDisableService.disableProduct(idProduto);

            // Retorna 200 OK com o DTO
            return ResponseEntity.ok(responseDTO);

        } catch (RuntimeException e) {
            // Captura a exceção e decide o Status HTTP
            String errorMessage = e.getMessage();

            // TRATAMENTO 404 (Not Found)
            // Verifica a mensagem de exceção para identificar recurso não encontrado
            if (errorMessage != null && errorMessage.contains("não encontrado")) {

                // Retorna 404 Not Found, montando o JSON de erro
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "erro", Map.of(
                                "codigo", "PRODUTO_NAO_ENCONTRADO",
                                "detalhe", errorMessage
                        )
                ));
            }

            // TRATAMENTO 401 (Unauthorized)
            // Verifica a mensagem para simular erro de permissão ou acesso negado
            if (errorMessage != null && (errorMessage.contains("não autorizado") || errorMessage.contains("acesso negado"))) {

                // Retorna 401 Unauthorized, montando o JSON de erro
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "erro", Map.of(
                                "codigo", "NAO_AUTORIZADO",
                                "detalhe", errorMessage
                        )
                ));
            }

            // Tratamento 500 (Erro Interno) para qualquer outro erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("erro", "Erro interno no servidor: " + errorMessage));
        }
    }
}