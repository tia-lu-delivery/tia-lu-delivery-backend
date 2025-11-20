package br.com.fooddelivery.tialudeliveryback.dto;

public record MerchantRequestDTO(
    String name,
    String cnpj,
    String address,
    Boolean active
){}

package br.com.fooddelivery.tialudeliveryback.dto;

public record MerchantResponseDTO(
    Long id,
    String name,
    String cnpj,
    String address,
    Boolean active
) {}
