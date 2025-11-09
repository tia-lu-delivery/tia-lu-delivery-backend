package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.EstabelecimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<EstabelecimentoEntity, String> {

    @Query("""
           select e
             from CardapioEntity c
             join c.estabelecimento e
            where c.id = :idCardapio
           """)
    Optional<EstabelecimentoEntity> findByCardapioId(@Param("idCardapio") String idCardapio);
}
