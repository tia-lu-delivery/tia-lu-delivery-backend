package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    
    // Buscar todos os restaurantes como ArrayList
    @Query("SELECT r FROM Restaurant r")
    ArrayList<Restaurant> findAllAsArrayList();
    
    // Buscar restaurantes por nome
    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    ArrayList<Restaurant> findByNomeContainingIgnoreCase(@Param("nome") String nome);
    
    // Buscar restaurantes por avaliação mínima
    @Query("SELECT r FROM Restaurant r WHERE r.avaliacaoMedia >= :avaliacaoMinima")
    ArrayList<Restaurant> findByAvaliacaoMediaGreaterThanEqual(@Param("avaliacaoMinima") Double avaliacaoMinima);
    
    // Buscar restaurantes com tempo médio de entrega
    @Query("SELECT r FROM Restaurant r WHERE r.tempoMedioEntrega = :tempoEntrega")
    ArrayList<Restaurant> findByTempoMedioEntrega(@Param("tempoEntrega") String tempoEntrega);
    
    // Buscar restaurantes com menu carregado (usando JOIN FETCH)
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menu WHERE r.id = :id")
    ArrayList<Restaurant> findByIdWithMenu(@Param("id") String id);
    
    // Método para buscar todos os restaurantes com seus menus carregados
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menu")
    ArrayList<Restaurant> findAllWithMenu();
    
    // Método default para converter List para ArrayList
    default ArrayList<Restaurant> findAllAsArrayListDefault() {
        return new ArrayList<>(findAll());
    }
}