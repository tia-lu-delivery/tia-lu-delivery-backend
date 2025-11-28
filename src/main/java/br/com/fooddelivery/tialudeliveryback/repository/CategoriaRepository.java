package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.Categoria;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Verifica se já existe uma categoria com o mesmo nome dentro do mesmo cardápio
    Optional<Categoria> findByNomeCategoriaAndCardapioId(String nomeCategoria, Long idCardapio);

    // Busca todas as categorias de um cardápio específico
    List<Categoria> findByCardapioId(Long idCardapio);

        //finalizado
}
