package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.api.dto.SearchResponseDTO;
import br.com.fooddelivery.tialudeliveryback.api.dto.RestaurantSearchResultDTO;
import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import br.com.fooddelivery.tialudeliveryback.repository.MenuItemRepository; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional; 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private SearchService searchService;

    private Restaurant restaurantA;
    private Restaurant restaurantB;
    private MenuItem item1;
    private MenuItem item2;
    private MenuItem item3;

    @BeforeEach
    void setup() {
        // --- Configurar Dados de Teste ---
        
        restaurantA = new Restaurant();
        restaurantA.setId("R1001");
        restaurantA.setNome("Burger Mania");
        restaurantA.setAvaliacaoMedia(4.8);
        restaurantA.setTempoMedioEntrega("30-45 min");

        restaurantB = new Restaurant();
        restaurantB.setId("R1002");
        restaurantB.setNome("Cantina Italiana");
        restaurantB.setAvaliacaoMedia(4.1);
        restaurantB.setTempoMedioEntrega("40-55 min");

        item1 = new MenuItem();
        item1.setId("P501");
        item1.setNome("Hamburguer Clássico com Cheddar");
        item1.setPreco(32.90);
        item1.setRestaurant(restaurantA);

        item2 = new MenuItem();
        item2.setId("P502");
        item2.setNome("Hamburguer Vegano");
        item2.setPreco(35.50);
        item2.setRestaurant(restaurantA);

        item3 = new MenuItem();
        item3.setId("P612");
        item3.setNome("Parmegiana Hamburguês");
        item3.setPreco(59.90);
        item3.setRestaurant(restaurantB);
    }

    @Test
    void searchRestaurantsByPlate_ShouldGroupItemsByRestaurant_SuccessCase() {
        String searchTerm = "hamburguer";
        
        when(menuItemRepository.findByNomeContainingIgnoreCase(searchTerm))
                .thenReturn(Arrays.asList(item1, item2, item3));

        SearchResponseDTO response = searchService.searchRestaurantsByPlate(searchTerm);

        // ==========================================================
        // FEEDBACK DE CONSOLE: Exibe o payload gerado
        // ==========================================================
        System.out.println("\n--- TESTE DE SUCESSO: Resultados Agrupados ---");
        System.out.println("Termo Pesquisado: " + response.getTermoPesquisado());
        System.out.println("Total de Resultados (Restaurantes): " + response.getTotalResultados());
        
        response.getResultados().forEach(r -> {
            System.out.println("  > Restaurante ID: " + r.getIdRestaurante() + " (" + r.getNomeRestaurante() + ")");
            System.out.println("    - Avaliação: " + r.getAvaliacaoMedia() + " | Entrega: " + r.getTempoMedioEntrega());
            System.out.println("    - Pratos Encontrados (" + r.getPratosEncontrados().size() + "):");
            r.getPratosEncontrados().forEach(p -> {
                System.out.println("      - [" + p.getIdPrato() + "] " + p.getNome() + " (R$" + p.getPreco() + ")");
            });
        });
        System.out.println("----------------------------------------------\n");
        // ==========================================================

        // Assertions (Verificações)
        assertNotNull(response);
        assertEquals(2, response.getTotalResultados(), "Deve encontrar 2 restaurantes distintos.");
        
        Optional<RestaurantSearchResultDTO> resultA = response.getResultados().stream()
                .filter(r -> "R1001".equals(r.getIdRestaurante()))
                .findFirst();
        
        Optional<RestaurantSearchResultDTO> resultB = response.getResultados().stream()
                .filter(r -> "R1002".equals(r.getIdRestaurante()))
                .findFirst();
        
        assertTrue(resultA.isPresent(), "O Restaurante R1001 deve estar presente na lista.");
        assertTrue(resultB.isPresent(), "O Restaurante R1002 deve estar presente na lista.");
        
        assertEquals(2, resultA.get().getPratosEncontrados().size(), "Restaurante A deve ter 2 pratos encontrados.");
        assertEquals(1, resultB.get().getPratosEncontrados().size(), "Restaurante B deve ter 1 prato encontrado.");
        
        verify(menuItemRepository, times(1)).findByNomeContainingIgnoreCase(searchTerm);
    }
    
    @Test
    void searchRestaurantsByPlate_ShouldReturnEmptyList_NoResultsFound() {
        String searchTerm = "Sushi de Picanha";
        
        when(menuItemRepository.findByNomeContainingIgnoreCase(searchTerm))
                .thenReturn(Collections.emptyList());

        SearchResponseDTO response = searchService.searchRestaurantsByPlate(searchTerm);

        // ==========================================================
        // FEEDBACK DE CONSOLE: Sem resultados
        // ==========================================================
        System.out.println("\n--- TESTE SEM RESULTADOS ---");
        System.out.println("Termo Pesquisado: " + response.getTermoPesquisado());
        System.out.println("Total de Resultados: " + response.getTotalResultados());
        System.out.println("----------------------------\n");
        // ==========================================================

        // Assertions (Verificações)
        assertEquals(0, response.getTotalResultados(), "O total deve ser 0.");
        assertTrue(response.getResultados().isEmpty(), "A lista de resultados deve ser vazia.");
    }
    
    @Test
    void searchRestaurantsByPlate_ShouldThrowException_WhenTermIsMissing() {
        assertThrows(IllegalArgumentException.class, () -> {
            searchService.searchRestaurantsByPlate(null);
        }, "Deve lançar exceção se o termo for null.");
        
        assertThrows(IllegalArgumentException.class, () -> {
            searchService.searchRestaurantsByPlate("");
        }, "Deve lançar exceção se o termo for vazio.");
    }
}