package com.fooddelivery.tialudeliveryback.repository;

import com.tialu.delivery.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, String> {
    
    Optional<Estabelecimento> findByIdEstabelecimentoAndAtivoTrue(String idEstabelecimento);
    
    boolean existsByCnpj(String cnpj);
}