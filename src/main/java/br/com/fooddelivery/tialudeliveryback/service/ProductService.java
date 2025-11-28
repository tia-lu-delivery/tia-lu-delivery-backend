package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.ProductResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.Product;
import br.com.fooddelivery.tialudeliveryback.mapper.ProductMapper;
import br.com.fooddelivery.tialudeliveryback.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponseDTO getProductDetails(Long idRestaurante, Long idProduto) {
        Product product = repository
                .findByRestaurantIdAndId(idRestaurante, idProduto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("O produto '%d' não foi encontrado no cardápio do restaurante '%d'.",
                                idProduto, idRestaurante)
                ));

        return mapper.toDTO(product);
    }
}

