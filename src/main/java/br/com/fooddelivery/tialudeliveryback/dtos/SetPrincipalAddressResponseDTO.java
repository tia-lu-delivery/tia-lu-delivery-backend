package br.com.fooddelivery.tialudeliveryback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetPrincipalAddressResponseDTO {
    private String status;
    private String detalhe;
    @JsonProperty("id_endereco_principal")
    private Long idEnderecoPrincipal;
}