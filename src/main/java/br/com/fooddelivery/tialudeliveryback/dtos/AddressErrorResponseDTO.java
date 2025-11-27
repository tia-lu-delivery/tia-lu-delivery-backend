package br.com.fooddelivery.tialudeliveryback.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressErrorResponseDTO {
    private String codigo;
    private String detalhe;
}
