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
public class CardapioDTO {

    private String idCardapio;

    private String nomeCardapio;

    private String dataAtualizacao;

    private String mensagem;

    private EstabelecimentoDTO estabelecimento;

    private List<CategoriaDTO> categorias;

}
