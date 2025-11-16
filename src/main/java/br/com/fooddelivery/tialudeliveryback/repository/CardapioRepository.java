package com.fooddelivery.tialudeliveryback.repository;

import com.tialu.delivery.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, String> {
    
    boolean existsByNomeCardapioAndEstabelecimentoIdEstabelecimento(
        String nomeCardapio, String idEstabelecimento);
    
    Optional<Cardapio> findByIdCardapioAndEstabelecimentoIdEstabelecimento(
        String idCardapio, String idEstabelecimento);
    
    List<Cardapio> findByEstabelecimentoIdEstabelecimento(String idEstabelecimento);
    
    @Query("SELECT c FROM Cardapio c WHERE c.nomeCardapio = :nome AND c.estabelecimento.idEstabelecimento = :estabelecimentoId")
    Optional<Cardapio> findByNomeAndEstabelecimento(
        @Param("nome") String nomeCardapio, 
        @Param("estabelecimentoId") String idEstabelecimento);
}