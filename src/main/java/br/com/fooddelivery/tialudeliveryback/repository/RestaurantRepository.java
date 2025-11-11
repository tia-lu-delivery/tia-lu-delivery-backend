package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RestaurantRepository {
    
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    
    public List<Restaurant> findByMenuItemNameContaining(String nomePrato) {
        if (nomePrato == null || nomePrato.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String termoPesquisa = nomePrato.toLowerCase().trim();
        
        List<MenuItem> itensEncontrados = menuItems.stream()
            .filter(item -> item.getNome().toLowerCase().contains(termoPesquisa))
            .collect(Collectors.toList());
        
        return itensEncontrados.stream()
            .map(MenuItem::getRestaurant)
            .distinct()
            .collect(Collectors.toList());
    }
    
    public void saveRestaurant(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(java.util.UUID.randomUUID().toString());
        }
        restaurants.add(restaurant);
        
        if (restaurant.getMenu() != null) {
            menuItems.addAll(restaurant.getMenu());
        }
    }
    
    public List<Restaurant> findAllRestaurants() {
        return new ArrayList<>(restaurants);
    }
}