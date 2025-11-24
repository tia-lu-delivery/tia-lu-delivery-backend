package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioFuncionamentoResponseDTO {
    private String idEstabelecimento;
    private String status;
    private String detalhe;
    private List<String> diasAbertos;
}