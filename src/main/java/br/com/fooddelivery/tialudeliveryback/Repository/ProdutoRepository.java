package br.com.fooddelivery.tialudeliveryback.Repository;

import br.com.fooddelivery.tialudeliveryback.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Modifying
    @Query("UPDATE Produto p SET p.disponivel = false WHERE p.id = :id")
    int marcarComoIndisponivel(@Param("id") Long id);
}