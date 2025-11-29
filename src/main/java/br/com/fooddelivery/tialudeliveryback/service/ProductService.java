package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.ProductResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.Product;
import br.com.fooddelivery.tialudeliveryback.exception.RestaurantNotFoundException;
import br.com.fooddelivery.tialudeliveryback.mapper.ProductMapper;
import br.com.fooddelivery.tialudeliveryback.repository.ProductRepository;
import br.com.fooddelivery.tialudeliveryback.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository,
                          RestaurantRepository restaurantRepository,
                          ProductMapper mapper) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    public ProductResponseDTO getProductDetails(Long idRestaurante, Long idProduto) {

        restaurantRepository.findById(idRestaurante)
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "O restaurante '" + idRestaurante + "' não existe ou está indisponível."
                ));

        Product product = productRepository
                .findByRestaurantIdAndId(idRestaurante, idProduto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("O produto '%d' não foi encontrado no cardápio do restaurante '%d'.",
                                idProduto, idRestaurante)
                ));

        return mapper.toDTO(product);
    }

    public List<ProductResponseDTO> listProducts(Long idRestaurante) {

        restaurantRepository.findById(idRestaurante)
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "O restaurante '" + idRestaurante + "' não existe ou está indisponível."
                ));

        List<Product> produtos = productRepository.findByRestaurantId(idRestaurante);

        if (produtos.isEmpty()) {
            throw new NoSuchElementException(
                    String.format("Nenhum produto encontrado para o restaurante '%d'.", idRestaurante)
            );
        }

        return produtos.stream()
                .map(mapper::toDTO)
                .toList();
    }
}
