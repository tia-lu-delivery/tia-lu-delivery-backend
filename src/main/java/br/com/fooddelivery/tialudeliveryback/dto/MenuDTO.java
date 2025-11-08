package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class MenuDTO {

    private String idCardapio;
    private String nomeCardapio;
    private String dataAtualizacao;
    private EstabelecimentoDTO estabelecimento;
    private List<CategoriaDTO> categorias;
    private String mensagem;

}
