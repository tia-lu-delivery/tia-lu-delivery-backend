package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;

    private String nomeCategoria;

    private int ordem;

    private Boolean disponivel;

    private List<ProdutoDTO> produtos;

}
