package br.com.fooddelivery.tialudeliveryback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fooddelivery.tialudeliveryback.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByMerchantIdAndId(String merchantId, String id);
}
