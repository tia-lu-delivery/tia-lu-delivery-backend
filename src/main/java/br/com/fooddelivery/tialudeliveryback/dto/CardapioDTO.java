package br.com.fooddelivery.tialudeliveryback.dto;

import br.com.fooddelivery.tialudeliveryback.entity.CategoriaEntity;
import br.com.fooddelivery.tialudeliveryback.entity.EstabelecimentoEntity;
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

    public EstabelecimentoDTO getEstabelecimento() {
        return estabelecimento;
    }
    public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }
    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

}
