package br.com.fooddelivery.tialudeliveryback.api.dto;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import java.math.BigDecimal;

public class MenuItemSearchResponseDTO {

    private Long idPrato;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    // Construtor a partir do MenuItem real
    public MenuItemSearchResponseDTO(MenuItem menuItem) {
        this.idPrato = menuItem.getId();
        this.nome = menuItem.getNome();
        this.descricao = menuItem.getDescricao();
        this.preco = menuItem.getPreco();
    }

    // Construtor para dados fixos (exemplo)
    public MenuItemSearchResponseDTO(String idPrato, String nome, double preco, String descricao) {
        this.idPrato = idPrato != null ? Long.valueOf(idPrato.replaceAll("\\D", "")) : null;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = BigDecimal.valueOf(preco);
    }

    // Getters
    public Long getIdPrato() { return idPrato; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
}
