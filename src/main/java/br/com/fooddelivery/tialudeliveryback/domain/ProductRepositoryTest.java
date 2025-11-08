package br.com.fooddelivery.tialudeliveryback.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.fooddelivery.tialudeliveryback.domain.Product;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("findByMerchantIdAndId encontra produto salvo para merchant correto")
    void findByMerchantIdAndId_returnsProductWhenExists() {
        Product p = new Product("P900", "R900", false);
        productRepository.save(p);

        Optional<Product> found = productRepository.findByMerchantIdAndId("R900", "P900");

        assertTrue(found.isPresent());
        assertEquals("P900", found.get().getId());
        assertEquals("R900", found.get().getMerchantId());
    }
}
