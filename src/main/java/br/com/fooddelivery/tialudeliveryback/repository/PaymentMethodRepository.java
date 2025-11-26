package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.model.PaymentMethod;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, String> {

    Optional<PaymentMethod> findByIdAndUserId(String id, String userId);

    List<PaymentMethod> findByUserId(String userId);

    @Modifying(clearAutomatically = true)
    @Query("update PaymentMethod p set p.principal = false where p.userId = :userId and p.id <> :exceptId")
    int unsetPrincipalForOthers(@Param("userId") String userId, @Param("exceptId") String exceptId);
}
