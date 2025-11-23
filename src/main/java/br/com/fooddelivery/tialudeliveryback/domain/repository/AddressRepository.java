package br.com.fooddelivery.tialudeliveryback.domain.repository;

import br.com.fooddelivery.tialudeliveryback.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserIdOrderByPadraoEntregaDesc(String userId);
}
