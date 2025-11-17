package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MenuItemRepository {
    
    private final List<MenuItem> menuItems = new ArrayList<>();
    
    // Método para buscar itens do menu por nome (case-insensitive e substring)
    public List<MenuItem> findByNomeContainingIgnoreCase(String nome) {
        return menuItems.stream()
                .filter(item -> item.getNome() != null && 
                               item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Método para buscar restaurantes que tenham pratos com o nome pesquisado
    public List<Restaurant> findRestaurantsByMenuItemName(String itemName) {
        List<MenuItem> matchingItems = findByNomeContainingIgnoreCase(itemName);
        
        // Agrupar por restaurante e remover duplicatas
        return matchingItems.stream()
                .map(MenuItem::getRestaurant)
                .distinct()
                .collect(Collectors.toList());
    }
    
    // Método auxiliar para popular dados de teste
    public void initializeSampleData() {
        // Criar alguns restaurantes de exemplo
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(UUID.randomUUID().toString());
        restaurant1.setNome("Restaurante Sabor Brasileiro");
        restaurant1.setAvaliacaoMedia(4.5);
        restaurant1.setTempoMedioEntrega("30-40 min");
        
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(UUID.randomUUID().toString());
        restaurant2.setNome("Pizzaria Italiana");
        restaurant2.setAvaliacaoMedia(4.8);
        restaurant2.setTempoMedioEntrega("25-35 min");
        
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setId(UUID.randomUUID().toString());
        restaurant3.setNome("Cantina Mexicana");
        restaurant3.setAvaliacaoMedia(4.2);
        restaurant3.setTempoMedioEntrega("35-45 min");
        
        // Criar itens do menu
        MenuItem item1 = new MenuItem();
        item1.setId(UUID.randomUUID().toString());
        item1.setNome("Feijoada Completa");
        item1.setPreco(35.90);
        item1.setDescricaoCurta("Feijoada tradicional com todas as accompanhamentos");
        item1.setRestaurant(restaurant1);
        
        MenuItem item2 = new MenuItem();
        item2.setId(UUID.randomUUID().toString());
        item2.setNome("Pizza Calabresa");
        item2.setPreco(49.90);
        item2.setDescricaoCurta("Pizza com calabresa fatiada e cebola");
        item2.setRestaurant(restaurant2);
        
        MenuItem item3 = new MenuItem();
        item3.setId(UUID.randomUUID().toString());
        item3.setNome("Pizza Margherita");
        item3.setPreco(42.90);
        item3.setDescricaoCurta("Pizza tradicional com molho de tomate, mussarela e manjericão");
        item3.setRestaurant(restaurant2);
        
        MenuItem item4 = new MenuItem();
        item4.setId(UUID.randomUUID().toString());
        item4.setNome("Burrito de Carne");
        item4.setPreco(28.50);
        item4.setDescricaoCurta("Burrito recheado com carne moída temperada");
        item4.setRestaurant(restaurant3);
        
        MenuItem item5 = new MenuItem();
        item5.setId(UUID.randomUUID().toString());
        item5.setNome("Feijoada Light");
        item5.setPreco(29.90);
        item5.setDescricaoCurta("Versão light da feijoada tradicional");
        item5.setRestaurant(restaurant1);
        
        // Adicionar à lista
        menuItems.add(item1);
        menuItems.add(item2);
        menuItems.add(item3);
        menuItems.add(item4);
        menuItems.add(item5);
        
        // Atualizar menus dos restaurantes
        restaurant1.setMenu(List.of(item1, item5));
        restaurant2.setMenu(List.of(item2, item3));
        restaurant3.setMenu(List.of(item4));
    }
    
    // Métodos auxiliares para CRUD
    public MenuItem save(MenuItem menuItem) {
        if (menuItem.getId() == null) {
            menuItem.setId(UUID.randomUUID().toString());
        }
        menuItems.add(menuItem);
        return menuItem;
    }
    
    public List<MenuItem> findAll() {
        return new ArrayList<>(menuItems);
    }
    
    public Optional<MenuItem> findById(String id) {
        return menuItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }
    
    public void deleteById(String id) {
        menuItems.removeIf(item -> item.getId().equals(id));
    }
}