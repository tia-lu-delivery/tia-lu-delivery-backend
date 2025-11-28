package br.com.fooddelivery.tialudeliveryback.domain.repository;

import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA mínimo para PaymentMethod.
 *
 * Observação: a entidade atualmente não contém referência explícita ao usuário/dono.
 * A validação de titularidade é feita no Service via heurística/reflexão (se possível)
 * até que o modelo de domínio seja estendido pela equipe responsável.
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

}
