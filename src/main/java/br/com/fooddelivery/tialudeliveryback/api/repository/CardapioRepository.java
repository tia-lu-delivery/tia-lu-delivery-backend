package com.tialu.api.repository;

import com.tialu.api.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

    // Busca segura: Garante que só retorna se o ID bater com o dono (CA-002)
    Optional<Cardapio> findByIdAndMerchantId(Long id, Long merchantId);

    // Necessário para a validação do CA-005 (Contagem)
    long countByMerchantId(Long merchantId);
}

//atualizado
