package br.com.fooddelivery.tialudeliveryback.api.dto;

import java.util.List;

public class SearchResponseDTO {

    private String termoPesquisado;
    private List<RestaurantSearchResultDTO> resultados;

    public SearchResponseDTO(String termoPesquisado, List<RestaurantSearchResultDTO> resultados) {
        this.termoPesquisado = termoPesquisado;
        this.resultados = resultados;
    }

    public String getTermoPesquisado() { return termoPesquisado; }
    public List<RestaurantSearchResultDTO> getResultados() { return resultados; }

    public int getTotalResultados() { return resultados.size(); }
}
