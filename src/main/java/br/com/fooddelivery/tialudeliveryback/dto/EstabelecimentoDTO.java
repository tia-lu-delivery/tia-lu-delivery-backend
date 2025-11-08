package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoDTO {

    private Long id;

    private String nomeFantasia;

}
