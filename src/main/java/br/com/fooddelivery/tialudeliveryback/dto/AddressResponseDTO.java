package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("detalhe")
    private String detalhe;

    @JsonProperty("id_endereco_excluido")
    private Long idEnderecoExcluido;

    public static AddressResponseDTO sucesso(Long idEndereco) {
        return AddressResponseDTO.builder()
                .status("sucesso")
                .detalhe("Endereço excluído com sucesso.")
                .idEnderecoExcluido(idEndereco)
                .build();
    }
}