package br.com.fooddelivery.tialudeliveryback.dto;

import org.springframework.beans.BeanUtils;

public class CategoriaDTO {

    private Long id;

    private String nomeCategoria;

    private int ordem;

    //-------------

    public CategoriaDTO(CategoriaDTO categoria) {
        BeanUtils.copyProperties(categoria, this);
    }
    public CategoriaDTO() {}

    //-----------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getOrdem() {
        return ordem;
    }
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

}
