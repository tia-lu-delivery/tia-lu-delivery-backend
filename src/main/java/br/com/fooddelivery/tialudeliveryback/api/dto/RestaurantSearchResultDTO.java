package br.com.fooddelivery.tialudeliveryback.api.dto;

import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantSearchResultDTO {

    private String idRestaurante;
    private String nomeRestaurante;
    private Double avaliacaoMedia;
    private String tempoMedioEntrega;
    private List<MenuItemSearchResponseDTO> pratosEncontrados;

    public RestaurantSearchResultDTO(Restaurant restaurant, List<MenuItemSearchResponseDTO> matchedItems) {
        this.idRestaurante = restaurant.getId();
        this.nomeRestaurante = restaurant.getNome();
        this.avaliacaoMedia = restaurant.getAvaliacaoMedia();
        this.tempoMedioEntrega = restaurant.getTempoMedioEntrega();
        this.pratosEncontrados = matchedItems;
    }

    // Getters
    public String getIdRestaurante() {
        return idRestaurante;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public Double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public String getTempoMedioEntrega() {
        return tempoMedioEntrega;
    }

    public List<MenuItemSearchResponseDTO> getPratosEncontrados() {
        return pratosEncontrados;
    }
}