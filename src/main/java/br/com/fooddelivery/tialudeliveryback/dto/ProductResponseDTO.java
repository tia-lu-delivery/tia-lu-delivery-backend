package br.com.fooddelivery.tialudeliveryback.dto;

import java.math.BigDecimal;

public class ProductResponseDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
    private String imageUrl;
    private Boolean active;

    public ProductResponseDTO(Long id, String name, BigDecimal price, String description,
                              Integer stockQuantity, String imageUrl, Boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getActive() {
        return active;
    }
}
