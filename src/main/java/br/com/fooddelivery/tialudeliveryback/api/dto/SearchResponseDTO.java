package br.com.fooddelivery.tialudeliveryback.api.dto;

import java.util.List;

public class SearchResponseDTO {

    private String termoPesquisado;
    private Integer totalResultados;
    private List<RestaurantSearchResultDTO> resultados;

    public SearchResponseDTO(String termoPesquisado, List<RestaurantSearchResultDTO> resultados) {
        this.termoPesquisado = termoPesquisado;
        this.resultados = resultados;
        this.totalResultados = resultados.size();
    }

    // Getters
    public String getTermoPesquisado() {
        return termoPesquisado;
    }

    public Integer getTotalResultados() {
        return totalResultados;
    }

    public List<RestaurantSearchResultDTO> getResultados() {
        return resultados;
    }
}