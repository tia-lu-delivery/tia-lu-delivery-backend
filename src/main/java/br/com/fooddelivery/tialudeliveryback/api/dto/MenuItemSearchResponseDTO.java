package br.com.fooddelivery.tialudeliveryback.api.dto;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;

public class MenuItemSearchResponseDTO {

    private String idPrato;
    private String nome;
    private Double preco;
    private String descricaoCurta;

    public MenuItemSearchResponseDTO(MenuItem menuItem) {
        this.idPrato = menuItem.getId();
        this.nome = menuItem.getNome();
        this.preco = menuItem.getPreco();
        this.descricaoCurta = menuItem.getDescricaoCurta();
    }

    // Getters
    public String getIdPrato() {
        return idPrato;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }
}