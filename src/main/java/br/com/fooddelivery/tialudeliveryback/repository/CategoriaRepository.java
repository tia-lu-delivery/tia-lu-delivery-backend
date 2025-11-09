package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, String> {

    @Query("""
       select distinct cat
         from CategoriaEntity cat
         join cat.cardapio card
         join fetch cat.produtos p
        where card.id = :idCardapio
          and p.disponivel = true
        order by cat.ordem asc, p.nomeProduto asc
       """)
    List<CategoriaEntity> findCategoriasComProdutosDisponiveis(@Param("idCardapio") String idCardapio);
    }