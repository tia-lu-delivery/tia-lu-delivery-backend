package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// ATENÇÃO: Mudamos de Long para String, pois sua Entidade usa String para o ID
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    // 1. Para validação de conflito (usado no Service)
    // O nome deve ser 'IdCardapio' para bater com o campo 'private String idCardapio' da Entidade
    boolean existsByNomeCategoriaAndIdCardapio(String nomeCategoria, String idCardapio);

    // 2. Para buscar categorias (se precisar futuramente)
    // Também corrigido para 'IdCardapio'
    List<Categoria> findByIdCardapio(String idCardapio);
}