package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagamentoRequestDTO {

    @NotBlank(message = "O ID do meio de pagamento é obrigatório.")
    private String idMeioPagamento;

    @NotBlank(message = "O CVV é obrigatório para pagamento com cartão.")
    private String cvv;

    @NotNull(message = "O número de parcelas é obrigatório.")
    @Min(value = 1, message = "O número de parcelas deve ser no mínimo 1.")
    private Integer parcelas = 1;

}