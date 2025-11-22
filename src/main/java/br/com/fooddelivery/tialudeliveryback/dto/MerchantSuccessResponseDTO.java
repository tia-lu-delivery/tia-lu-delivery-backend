package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO de resposta para operações bem-sucedidas de criação.
 * Retorna apenas o ID gerado e uma mensagem de confirmação.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantSuccessResponseDTO {

    // O identificador único gerado pelo banco de dados após a persistência
    private UUID idEstabelecimento;

    // Mensagem informativa para o cliente da API
    private String mensagem;
}