package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;

/**
 * Serviço da história DWOO-0018: habilitar produto para venda (Lisboa).
 */
public interface ProductService {

    /**
     * Habilita (disponibiliza) um produto no cardápio do estabelecimento.
     *
     * @param merchantId ID do estabelecimento
     * @param productId  ID do produto
     * @return resposta com status de ativação e flags
     */
    ProductEnableResponse enableProduct(String merchantId, String productId);
}
