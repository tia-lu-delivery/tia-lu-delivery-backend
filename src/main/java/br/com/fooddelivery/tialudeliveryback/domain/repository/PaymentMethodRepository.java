package br.com.fooddelivery.tialudeliveryback.domain.repository;

import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

    /**
     * Busca um meio de pagamento pelo seu identificador e pelo identificador
     * do usuário autenticado (dono da carteira).
     *
     * Atende aos critérios:
     * - CA-002: garante que o meio de pagamento pertence à conta autenticada;
     * - CA-006: permite retornar 404 quando não encontrado ou não pertencer ao usuário.
     *
     * @param idMeioPagamento identificador do meio de pagamento (ex: "MP001")
     * @param userId          identificador do usuário autenticado (extraído do token)
     * @return Optional com PaymentMethod, vazio se não encontrado ou não pertencer ao usuário
     */
    Optional<PaymentMethod> findByIdAndUserId(String idMeioPagamento, String userId);
}
