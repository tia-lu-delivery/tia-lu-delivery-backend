package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;

    private String nomeProduto;

    private String descricao;

    private Double precoUnitario;

    private String imagemUrl;

    private Boolean disponivel;

    private Integer estoque;

}
