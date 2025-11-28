package br.com.fooddelivery.tialudeliveryback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private String status;
    private String detalhe;
    private Long id_excluido;
}