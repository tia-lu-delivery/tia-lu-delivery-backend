package br.com.fooddelivery.tialudeliveryback.dto;

import org.springframework.beans.BeanUtils;

public class EstabelecimentoDTO {

    private Long id;

    private String nomeFantasia;

    //---------------

    public EstabelecimentoDTO(EstabelecimentoDTO estabelecimento) {
        BeanUtils.copyProperties(estabelecimento, this);
    }
    public EstabelecimentoDTO() {}

    //--------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String  getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
