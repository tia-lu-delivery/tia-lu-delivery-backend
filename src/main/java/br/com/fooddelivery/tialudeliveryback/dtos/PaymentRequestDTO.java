package br.com.fooddelivery.tialudeliveryback.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    // Classe de teste

    private String numeroCartao;
    private Integer validadeMes;
    private Integer validadeAno;
    private String cvv;
    private String nomeTitular;
    private String cpfTitular;
    private String tipoCartao;
}
