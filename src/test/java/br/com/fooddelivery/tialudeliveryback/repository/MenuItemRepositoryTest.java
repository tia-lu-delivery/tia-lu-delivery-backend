package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MenuItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    void findByNomeContainingIgnoreCase_ShouldReturnMatchingItems_WhenTermExists() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setNome("Restaurante Teste");
        restaurant.setAvaliacaoMedia(4.5);
        restaurant.setTempoMedioEntrega("30-40 min");
        Restaurant savedRestaurant = entityManager.persistAndFlush(restaurant);

        MenuItem item1 = new MenuItem();
        item1.setNome("Hamburguer Clássico");
        item1.setPreco(25.90);
        item1.setDescricaoCurta("Hamburguer tradicional com queijo");
        item1.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item1);

        MenuItem item2 = new MenuItem();
        item2.setNome("Hamburguer Vegano");
        item2.setPreco(28.50);
        item2.setDescricaoCurta("Hamburguer vegetariano");
        item2.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item2);

        MenuItem item3 = new MenuItem();
        item3.setNome("Pizza Margherita");
        item3.setPreco(35.00);
        item3.setDescricaoCurta("Pizza tradicional");
        item3.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item3);

        // Act
        List<MenuItem> result = menuItemRepository.findByNomeContainingIgnoreCase("hamburguer");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size(), "Deve encontrar 2 itens com 'hamburguer' no nome");
        
        // Verifica se os itens corretos foram retornados
        assertTrue(result.stream().anyMatch(item -> "Hamburguer Clássico".equals(item.getNome())));
        assertTrue(result.stream().anyMatch(item -> "Hamburguer Vegano".equals(item.getNome())));
        assertFalse(result.stream().anyMatch(item -> "Pizza Margherita".equals(item.getNome())));
        
        // Console output para verificação visual
        System.out.println("\n--- TESTE: Busca por 'hamburguer' ---");
        System.out.println("Termo pesquisado: hamburguer");
        System.out.println("Itens encontrados: " + result.size());
        result.forEach(item -> 
            System.out.println("  - " + item.getNome() + " (R$ " + item.getPreco() + ")")
        );
        System.out.println("------------------------------------\n");
    }

    @Test
    void findByNomeContainingIgnoreCase_ShouldBeCaseInsensitive() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setNome("Restaurante Teste");
        restaurant.setAvaliacaoMedia(4.5);
        restaurant.setTempoMedioEntrega("30-40 min");
        Restaurant savedRestaurant = entityManager.persistAndFlush(restaurant);

        MenuItem item1 = new MenuItem();
        item1.setNome("HAMBURGUER ARTESANAL");
        item1.setPreco(32.90);
        item1.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item1);

        MenuItem item2 = new MenuItem();
        item2.setNome("hamburguer caseiro");
        item2.setPreco(29.90);
        item2.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item2);

        // Act - Testando diferentes combinações de case
        List<MenuItem> result1 = menuItemRepository.findByNomeContainingIgnoreCase("HAMBURGUER");
        List<MenuItem> result2 = menuItemRepository.findByNomeContainingIgnoreCase("hamburguer");
        List<MenuItem> result3 = menuItemRepository.findByNomeContainingIgnoreCase("Hamburguer");

        // Assert
        assertEquals(2, result1.size(), "Deve encontrar 2 itens independente do case (maiúsculo)");
        assertEquals(2, result2.size(), "Deve encontrar 2 itens independente do case (minúsculo)");
        assertEquals(2, result3.size(), "Deve encontrar 2 itens independente do case (misturado)");
        
        System.out.println("\n--- TESTE: Case Insensitive ---");
        System.out.println("Busca por 'HAMBURGUER': " + result1.size() + " resultados");
        System.out.println("Busca por 'hamburguer': " + result2.size() + " resultados");
        System.out.println("Busca por 'Hamburguer': " + result3.size() + " resultados");
        System.out.println("--------------------------------\n");
    }

    @Test
    void findByNomeContainingIgnoreCase_ShouldReturnPartialMatches() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setNome("Restaurante Teste");
        restaurant.setAvaliacaoMedia(4.5);
        restaurant.setTempoMedioEntrega("30-40 min");
        Restaurant savedRestaurant = entityManager.persistAndFlush(restaurant);

        MenuItem item1 = new MenuItem();
        item1.setNome("X-Tudo Completo");
        item1.setPreco(35.90);
        item1.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item1);

        MenuItem item2 = new MenuItem();
        item2.setNome("X-Salada Especial");
        item2.setPreco(28.50);
        item2.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item2);

        MenuItem item3 = new MenuItem();
        item3.setNome("X-Bacon");
        item3.setPreco(30.00);
        item3.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item3);

        // Act - Testando busca por substring
        List<MenuItem> result = menuItemRepository.findByNomeContainingIgnoreCase("X-");

        // Assert
        assertEquals(3, result.size(), "Deve encontrar todos os itens que começam com 'X-'");
        
        System.out.println("\n--- TESTE: Busca por Substring 'X-' ---");
        System.out.println("Termo pesquisado: X-");
        System.out.println("Itens encontrados: " + result.size());
        result.forEach(item -> 
            System.out.println("  - " + item.getNome() + " (R$ " + item.getPreco() + ")")
        );
        System.out.println("---------------------------------------\n");
    }

    @Test
    void findByNomeContainingIgnoreCase_ShouldReturnEmptyList_WhenNoMatches() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setNome("Restaurante Teste");
        restaurant.setAvaliacaoMedia(4.5);
        restaurant.setTempoMedioEntrega("30-40 min");
        Restaurant savedRestaurant = entityManager.persistAndFlush(restaurant);

        MenuItem item1 = new MenuItem();
        item1.setNome("Pizza Calabresa");
        item1.setPreco(45.90);
        item1.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item1);

        // Act
        List<MenuItem> result = menuItemRepository.findByNomeContainingIgnoreCase("sushi");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Deve retornar lista vazia quando não há matches");
        
        System.out.println("\n--- TESTE: Sem Resultados ---");
        System.out.println("Termo pesquisado: sushi");
        System.out.println("Itens encontrados: " + result.size());
        System.out.println("Resultado: Lista vazia ✓");
        System.out.println("----------------------------\n");
    }

    @Test
    void findByNomeContainingIgnoreCase_ShouldHandleSpecialCharacters() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setNome("Restaurante Teste");
        restaurant.setAvaliacaoMedia(4.5);
        restaurant.setTempoMedioEntrega("30-40 min");
        Restaurant savedRestaurant = entityManager.persistAndFlush(restaurant);

        MenuItem item1 = new MenuItem();
        item1.setNome("Pão de Queijo");
        item1.setPreco(8.90);
        item1.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item1);

        MenuItem item2 = new MenuItem();
        item2.setNome("Açaí na Tigela");
        item2.setPreco(18.50);
        item2.setRestaurant(savedRestaurant);
        entityManager.persistAndFlush(item2);

        // Act
        List<MenuItem> result1 = menuItemRepository.findByNomeContainingIgnoreCase("queijo");
        List<MenuItem> result2 = menuItemRepository.findByNomeContainingIgnoreCase("açai");

        // Assert
        assertEquals(1, result1.size(), "Deve encontrar item com caracteres especiais");
        assertEquals(1, result2.size(), "Deve encontrar item com acentuação");
        
        System.out.println("\n--- TESTE: Caracteres Especiais ---");
        System.out.println("Busca por 'queijo': " + result1.size() + " resultado");
        System.out.println("Busca por 'açai': " + result2.size() + " resultado");
        System.out.println("-----------------------------------\n");
    }
}