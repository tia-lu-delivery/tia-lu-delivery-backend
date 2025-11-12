package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.domain.Product;
import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;

/**
 * Mapper simples entre entidade e DTO de resposta.
 */
public final class ProductMapper {

    private ProductMapper() {}

    public static ProductEnableResponse toEnableResponse(Product product) {
        // Compatibilidade: determina o campo 'detalhe' com base no estado atual de disponibilidade
        return toEnableResponse(product, product != null && product.isDisponivel(), false);
    }

    /**
     * Converte entidade em DTO de resposta indicando se o estado foi alterado nesta operação.
     *
     * @param product entidade
     * @param disponivel atual flag de disponibilidade
     * @param changed true se a operação alterou o estado para disponível
     * @return DTO
     */
    public static ProductEnableResponse toEnableResponse(Product product, boolean disponivel, boolean changed) {
        if (product == null) return null;
        String status = disponivel ? "ativado" : "desativado";
        String detalhe;
        if (disponivel) {
            detalhe = changed ? "Produto reativado e marcado como disponível." : "Produto já estava disponível.";
        } else {
            detalhe = "Produto desativado.";
        }

        return ProductEnableResponse.builder()
                .id_produto(product.getId())
                .status(status)
                .detalhe(detalhe)
                .disponivel(disponivel)
                .build();
    }
}
