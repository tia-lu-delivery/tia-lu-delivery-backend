package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    /**
     * Busca itens de menu cujo nome contenha o termo de pesquisa,
     * ignorando o case.
     */
    List<MenuItem> findByNomeContainingIgnoreCase(String nomeTermo);
}
