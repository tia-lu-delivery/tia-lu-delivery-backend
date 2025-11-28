package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByRestaurantIdAndId(Long restaurantId, Long id);
}

