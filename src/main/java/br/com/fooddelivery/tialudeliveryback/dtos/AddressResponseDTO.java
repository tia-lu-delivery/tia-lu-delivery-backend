package br.com.fooddelivery.tialudeliveryback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
    private Long id;
    private String rua;
    @JsonProperty("padrao_entrega")
    private boolean principal;
}