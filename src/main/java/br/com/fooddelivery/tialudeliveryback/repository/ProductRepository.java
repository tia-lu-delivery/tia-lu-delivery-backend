package br.com.fooddelivery.tialudeliveryback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fooddelivery.tialudeliveryback.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
