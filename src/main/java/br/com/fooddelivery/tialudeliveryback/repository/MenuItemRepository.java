package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
    
    // Buscar todos os itens do menu como ArrayList
    @Query("SELECT m FROM MenuItem m")
    ArrayList<MenuItem> findAllAsArrayList();
    
    // Buscar itens do menu por restaurante
    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId")
    ArrayList<MenuItem> findByRestaurantId(@Param("restaurantId") String restaurantId);
    
    // Buscar itens do menu por nome (case insensitive)
    @Query("SELECT m FROM MenuItem m WHERE LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    ArrayList<MenuItem> findByNomeContainingIgnoreCase(@Param("nome") String nome);
    
    // Buscar itens do menu por faixa de preço
    @Query("SELECT m FROM MenuItem m WHERE m.preco BETWEEN :precoMin AND :precoMax")
    ArrayList<MenuItem> findByPrecoBetween(@Param("precoMin") Double precoMin, 
                                          @Param("precoMax") Double precoMax);
    
    // Método default para converter List para ArrayList
    default ArrayList<MenuItem> findAllAsArrayListDefault() {
        return new ArrayList<>(findAll());
    }
}