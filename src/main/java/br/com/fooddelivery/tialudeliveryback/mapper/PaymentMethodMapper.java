package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.PaymentMethod;

public class PaymentMethodMapper {

    public static PaymentMethodResponseDTO toDTO(PaymentMethod entity) {
        return PaymentMethodResponseDTO.builder()
                .idMeioPagamento(entity.getId())
                .status("atualizado")
                .detalhe("Meio de pagamento atualizado com sucesso.")
                .bandeira(entity.getBandeira())
                .ultimosDigitos(entity.getUltimosDigitos())
                .tipoCartao(entity.getTipoCartao().name())
                .validade(String.format("%02d/%d",
                        entity.getValidadeMes(),
                        entity.getValidadeAno() % 100))
                .build();
    }
}
