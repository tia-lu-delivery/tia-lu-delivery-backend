package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.repository.model.Produto; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Interface para operações com produtos no banco de dados
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Marca o produto como indisponível no banco de dados
    // Retorna 1 se encontrou e atualizou, ou 0 se o produto não foi encontrado
    @Modifying
    @Query("UPDATE Produto p SET p.disponivel = false WHERE p.id = :id")
    int marcarComoIndisponivel(@Param("id") Long id);

}