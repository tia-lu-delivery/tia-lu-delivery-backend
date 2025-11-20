package br.com.fooddelivery.tialudeliveryback.domain.repository;

import br.com.fooddelivery.tialudeliveryback.domain.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    
    Optional<Merchant> findByCnpj(String cnpj);
    
   
}