package br.com.fooddelivery.tialudeliveryback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Busca pelo ID e menu para garantir que a categoria pertence àquele cardápio
    Optional<Categoria> findByIdAndMenuId(Long id, Long menuId);

}