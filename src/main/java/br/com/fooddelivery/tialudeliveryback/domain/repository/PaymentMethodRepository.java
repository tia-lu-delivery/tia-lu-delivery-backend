package br.com.fooddelivery.tialudeliveryback.domain.repository;

import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
    Optional<PaymentMethod> findByIdAndUserId(String id, String userId);
}
