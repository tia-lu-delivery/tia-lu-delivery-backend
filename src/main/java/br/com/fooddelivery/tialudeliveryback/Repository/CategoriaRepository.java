package br.com.fooddelivery.tialudeliveryback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fooddelivery.tialudeliveryback.Entity.Categoria;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Busca pelo ID e pelo ID do menu (via propriedade menu.id) para garantir que a categoria pertence àquele cardápio
    Optional<Categoria> findByIdAndMenu_Id(Long id, Long menuId);

}