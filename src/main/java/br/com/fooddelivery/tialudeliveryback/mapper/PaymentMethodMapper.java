package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.PaymentMethod;

public class PaymentMethodMapper {

    public static PaymentMethodResponseDTO toDTO(PaymentMethod entity) {
        PaymentMethodResponseDTO dto = new PaymentMethodResponseDTO();
        dto.setIdMeioPagamento(String.valueOf(entity.getId()));
        dto.setStatus("atualizado");
        dto.setDetalhe("Meio de pagamento atualizado com sucesso.");

        PaymentMethodResponseDTO.DadosExibicaoDTO dados = new PaymentMethodResponseDTO.DadosExibicaoDTO();
        dados.setBandeira(entity.getBandeira());
        dados.setUltimosDigitos(entity.getUltimosDigitos());
        dados.setTipoCartao(entity.getTipoCartao() != null ? entity.getTipoCartao().name() : null);
        dados.setValidade(String.format("%02d/%d",
            entity.getValidadeMes(),
            entity.getValidadeAno() % 100));

        dto.setDadosExibicao(dados);
        return dto;
    }
}
