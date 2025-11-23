package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDeleteResponseDTO {
    
    private String status;
    private String detalhe;
    private String id_meio_pagamento_excluido;
    
    public static PaymentMethodDeleteResponseDTO success(String paymentMethodId) {
        return new PaymentMethodDeleteResponseDTO(
            "sucesso",
            "Meio de pagamento removido da carteira (status inativado).",
            paymentMethodId
        );
    }
}
