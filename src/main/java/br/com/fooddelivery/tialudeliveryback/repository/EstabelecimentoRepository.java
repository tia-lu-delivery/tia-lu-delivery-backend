package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.EstabelecimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<EstabelecimentoEntity, String> {

    @Query("""
           select e
             from EstabelecimentoEntity e
            where e.id = :idEstabelecimento
           """)
    Optional<EstabelecimentoEntity> findById(@Param("idEstabelecimento") String idEstabelecimento);

}
