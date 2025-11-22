package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, String> {

    /**
     * Busca itens de menu cujo nome contenha o termo de pesquisa,
     * ignorando o case (Critério CA-03).
     * @param nomeTermo Termo a ser pesquisado no nome do prato.
     * @return Lista de MenuItem que correspondem ao critério.
     */
    List<MenuItem> findByNomeContainingIgnoreCase(String nomeTermo);
}