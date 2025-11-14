package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import br.com.fooddelivery.tialudeliveryback.repository.MenuItemRepository;
import br.com.fooddelivery.tialudeliveryback.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    public ArrayList<Restaurant> buscarTodosRestaurantes() {
        return restaurantRepository.findAllAsArrayList();
    }
    
    public ArrayList<MenuItem> buscarMenuPorRestaurante(String restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
    
    public ArrayList<Restaurant> buscarRestaurantesPorAvaliacao(Double avaliacaoMinima) {
        return restaurantRepository.findByAvaliacaoMediaGreaterThanEqual(avaliacaoMinima);
    }
    
    public ArrayList<MenuItem> buscarItensPorFaixaPreco(Double precoMin, Double precoMax) {
        return menuItemRepository.findByPrecoBetween(precoMin, precoMax);
    }
    
    public ArrayList<Restaurant> buscarRestaurantesPorNome(String nome) {
        return restaurantRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public ArrayList<MenuItem> buscarItensPorNome(String nome) {
        return menuItemRepository.findByNomeContainingIgnoreCase(nome);
    }
}