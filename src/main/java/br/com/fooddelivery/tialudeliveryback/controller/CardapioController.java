package com.tialu.api.controller;

import com.tialu.api.dto.ResponseDTO;
import com.tialu.api.service.CardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant/menu")
public class CardapioController {

    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    // Método DELETE conforme CA-003 e rota definida
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteMenu(@PathVariable Long id) {
        Long merchantIdLogado = 123L;

        cardapioService.excluirCardapio(id, merchantIdLogado);

        // CA-006: Retorno 200 com corpo de confirmação
        return ResponseEntity.ok(new ResponseDTO(
            "sucesso",
            "Cardápio e todos os seus itens associados foram excluídos permanentemente.",
            id
        ));
    }
}