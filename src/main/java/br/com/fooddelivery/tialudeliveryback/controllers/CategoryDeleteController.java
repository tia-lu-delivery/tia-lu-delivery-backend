package br.com.fooddelivery.tialudeliveryback.controllers;

import br.com.fooddelivery.tialudeliveryback.DTOs.DeleteCategoryResDTO;
import br.com.fooddelivery.tialudeliveryback.Service.CategoryDeleteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/merchant/menu/{id_cardapio}/categories")
// Controller para deletar categoria de produtos e todos os produtos vinculados a ela
public class CategoryDeleteController {

    private final CategoryDeleteService categoryDeleteService;

    public CategoryDeleteController(CategoryDeleteService categoryDeleteService) {
        // Define o Service que será usado para a lógica de exclusão
        this.categoryDeleteService = categoryDeleteService;
    }

    /*
     * Endpoint para excluir uma categoria e seus produtos associados (em cascata)
     * O Controller mapeia o código de erro retornado pelo Service no DTO para o Status HTTP
     */
    @DeleteMapping("/{id_categoria}") // Mapeia o método DELETE para a exclusão do recurso
    public ResponseEntity<?> deleteCategory(
            // Captura o ID do cardápio definido na URL base
            @PathVariable("id_cardapio") Long idCardapio,
            // Captura o ID da Categoria a ser excluída, na parte final da URL
            @PathVariable("id_categoria") Long idCategoria) {

        // O controller chama o service, que retorna o DTO (seja de sucesso ou de erro)
        DeleteCategoryResDTO responseDTO = categoryDeleteService.deleteCategory(idCardapio, idCategoria);

        if (responseDTO.getErro() != null){

            String codigoErro = responseDTO.getErro().getCodigo();

            // Mapeamento 404 (Not Found)
            if ("CATEGORIA_NAO_ENCONTRADA".equals(codigoErro)){
                // Retorna 404 com o corpo de erro já formatado pelo Service/Mapper
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
            }

            // Mapeamento 401 (Unauthorized)
            if ("NAO_AUTORIZADO".equals(codigoErro)){
                // Retorna 401 com o corpo de erro já formatado pelo Service/Mapper
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
            }

            // Tratamento 500 (Erro Interno)
            // Retorna 500, pois o erro foi interno e não foi mapeado (ou é inesperado)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
            // Se o campo "erro" é nulo, o Service retornou um sucesso. Retorna 200 OK
            return ResponseEntity.ok(responseDTO);
    }
}