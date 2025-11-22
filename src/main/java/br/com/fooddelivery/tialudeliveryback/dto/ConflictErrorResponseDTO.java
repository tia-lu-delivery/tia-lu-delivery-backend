package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO padronizado para retorno de erros de conflito (ex: dados duplicados).
 * Estrutura a mensagem de erro para facilitar o entendimento pelo Frontend.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictErrorResponseDTO {

    // Código interno do erro para mapeamento no front (ex: DUPLICATE_ENTITY)
    private String codigoErro;

    // Descrição legível do erro ocorrido
    private String mensagem;

    // Instrução para o usuário sobre como proceder
    private String acaoSugerida;
}