package br.com.fooddelivery.tialudeliveryback.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fooddelivery.tialudeliveryback.domain.Product;
import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;
import br.com.fooddelivery.tialudeliveryback.exception.ProductNotFoundException;
import br.com.fooddelivery.tialudeliveryback.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        service = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("enableProduct lança ProductNotFoundException se produto não existir")
    void enableProduct_whenNotFound_thenThrow() {
        when(productRepository.findByMerchantIdAndId("R404", "PX")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> service.enableProduct("R404", "PX"));
    }

    @Test
    @DisplayName("enableProduct reativa produto indisponível e persiste alteração")
    void enableProduct_reactivatesAndSaves_whenPreviouslyUnavailable() {
        Product p = new Product("P501", "R1001", false);
        when(productRepository.findByMerchantIdAndId("R1001", "P501")).thenReturn(Optional.of(p));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductEnableResponse resp = service.enableProduct("R1001", "P501");

        assertNotNull(resp);
        assertTrue(resp.isDisponivel());
        assertEquals("ativado", resp.getStatus());
        assertEquals("Produto reativado e marcado como disponível.", resp.getDetalhe());
        verify(productRepository).save(p);
    }

    @Test
    @DisplayName("enableProduct não persiste quando produto já está disponível")
    void enableProduct_noSave_whenAlreadyAvailable() {
        Product p = new Product("P502", "R1001", true);
        when(productRepository.findByMerchantIdAndId("R1001", "P502")).thenReturn(Optional.of(p));

        ProductEnableResponse resp = service.enableProduct("R1001", "P502");

        assertNotNull(resp);
        assertTrue(resp.isDisponivel());
        assertEquals("Produto já estava disponível.", resp.getDetalhe());
        verify(productRepository, never()).save(any(Product.class));
    }
}
