package br.com.fooddelivery.tialudeliveryback.dto;

public record MerchantRequestDTO(
    String name,
    String cnpj,
    String address,
    Boolean active
) {}

