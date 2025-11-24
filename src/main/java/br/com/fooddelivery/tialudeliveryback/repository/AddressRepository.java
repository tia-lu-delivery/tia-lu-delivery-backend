package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUserIdAndId(String userId, Long id);

    @Modifying
    @Query("UPDATE Address a SET a.padraoEntrega = false WHERE a.userId = :userId AND a.padraoEntrega = true")
    void clearDefaultForUser(@Param("userId") String userId);
}
