package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CardapioRepository extends JpaRepository<CardapioEntity, String> {
    @Override
    @NonNull
    Optional<CardapioEntity> findById(@NonNull String id);

    @Query("""
           select distinct c
             from CardapioEntity c
             join fetch c.estabelecimento e
             join fetch c.categorias cat
             join fetch cat.produtos p
             where c.id = :id
              and p.disponivel = true
            order by cat.ordem asc, p.nomeProduto asc
           """)
    Optional<CardapioEntity> findByIdComCategoriasEProdutosDisponiveis(@Param("id") String id);
}
