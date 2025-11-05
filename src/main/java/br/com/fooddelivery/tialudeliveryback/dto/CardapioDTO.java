package br.com.fooddelivery.tialudeliveryback.dto;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class CardapioDTO {

    private Long id;

    private String nomeCardapio;

    private String dataAtualizacao;

    private EstabelecimentoDTO estabelecimento;

    private List<CategoriaDTO> categorias;


    //--------------------------------

    public CardapioDTO(CardapioDTO cardapio) {
        BeanUtils.copyProperties(cardapio, this);
    }
    public CardapioDTO() {}

    //-----------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCardapio() {
        return nomeCardapio;
    }

    public void setNomeCardapio(String nomeCardapio) {
        this.nomeCardapio = nomeCardapio;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}
