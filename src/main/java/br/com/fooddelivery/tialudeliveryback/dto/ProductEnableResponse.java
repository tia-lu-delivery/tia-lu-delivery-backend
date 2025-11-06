package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de resposta para a ação de habilitar produto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductEnableResponse {

    // Mantém nomes conforme contrato da história
    private String id_produto;
    private String status;   // ex.: "ativado"
    private String detalhe;  // ex.: "Produto reativado e marcado como disponível."
    private boolean disponivel;
}
