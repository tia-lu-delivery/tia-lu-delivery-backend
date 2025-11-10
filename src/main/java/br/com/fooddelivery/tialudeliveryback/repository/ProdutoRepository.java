package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {

    @Query("""
           select p
             from ProdutoEntity p
             join fetch p.categoria cat
             join fetch cat.cardapio card
            where card.id = :idCardapio
              and p.disponivel = true
            order by cat.ordem asc, p.nomeProduto asc
           """)
    List<ProdutoEntity> findDisponiveisByCardapio(@Param("idCardapio") String idCardapio);
}
