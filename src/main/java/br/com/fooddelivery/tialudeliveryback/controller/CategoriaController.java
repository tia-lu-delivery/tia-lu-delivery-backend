package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fooddelivery.tialudeliveryback.dto.request.CategoriaRequestDTO; 
import br.com.fooddelivery.tialudeliveryback.dto.response.CategoriaResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.CategoriaService;

import jakarta.validation.Valid; // Garante que você está usando Jakarta
import java.net.URI;

@RestController
@RequestMapping("/api/v1") // Rota Base mais geral
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    /**
     * Cria uma nova categoria e a associa ao cardápio (POST /api/v1/menu/{idCardapio}/categories).
     */
    @PostMapping("/menu/{idCardapio}/categories")
    public ResponseEntity<CategoriaResponseDTO> createCategoria(
            @PathVariable("idCardapio") String idCardapio,
            @Valid @RequestBody CategoriaRequestDTO requestDTO) {
        
        // 1. Chama o Service (que valida 404 e 409, lançando exceções)
        CategoriaResponseDTO response = categoriaService.createCategoria(idCardapio, requestDTO);
        
        // 2. Constrói a URI do novo recurso (prática recomendada para 201 Created)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") // Assumindo que a categoria pode ser acessada por um ID único
                .buildAndExpand(response.getIdCategoria())
                .toUri();
        
        // 3. Retorna 201 Created com a localização (Cenário de Sucesso 1)
        return ResponseEntity.created(location).body(response);
    }
}