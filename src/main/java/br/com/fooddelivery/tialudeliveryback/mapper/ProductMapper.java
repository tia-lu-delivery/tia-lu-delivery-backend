package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.dto.ProductResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO toDTO(Product entity) {
        if (entity == null) return null;

        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getStockQuantity(),
                entity.getImageUrl(),
                entity.getActive()
        );
    }
}
