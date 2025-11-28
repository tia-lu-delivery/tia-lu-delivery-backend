package com.tialu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private String status;
    private String detalhe;
    private Long id_excluido;
}