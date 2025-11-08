package br.com.fooddelivery.tialudeliveryback.exception;

/**
 * Exceção de domínio para produto não encontrado para um estabelecimento.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productId, String merchantId) {
        super("O produto com ID '" + productId + "' não foi encontrado no estabelecimento '" + merchantId + "'.");
    }
}
