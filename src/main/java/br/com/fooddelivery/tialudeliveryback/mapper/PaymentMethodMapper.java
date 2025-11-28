package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.enums.TipoCartao;

public class PaymentMethodMapper {

    public static PaymentMethodResponseDTO toDTO(PaymentMethod entity) {

        String numero = entity.getNumeroCartao();
        String ultimosDigitos = numero.substring(numero.length() - 4);

        String validadeMascarada = String.format(
                "%02d/%02d",
                entity.getValidadeMes(),
                entity.getValidadeAno() % 100
        );

        return new PaymentMethodResponseDTO(
                entity.getId(),
                entity.getBandeira(),
                ultimosDigitos,
                entity.getTipoCartao().name(),
                validadeMascarada
        );
    }
}
