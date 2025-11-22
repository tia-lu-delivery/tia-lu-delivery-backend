package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
    
    /**
     * Busca itens do menu cujo nome contenha o termo pesquisado (case-insensitive)
     * Atende ao critério CA-003: pesquisa flexível por substring e case-insensitive
     * 
     * @param nome Termo de pesquisa para o nome do prato
     * @return Lista de MenuItem que correspondem à pesquisa
     */
    List<MenuItem> findByNomeContainingIgnoreCase(String nome);
}