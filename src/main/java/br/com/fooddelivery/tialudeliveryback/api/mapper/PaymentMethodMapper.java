package br.com.fooddelivery.tialudeliveryback.api.mapper;


import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodDTO;
import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {

    public PaymentMethodDTO toDTO(PaymentMethod entity) {
        if (entity == null) {
            return null;
        }

        PaymentMethodDTO dto = new PaymentMethodDTO();

        dto.setIdMeioPagamento(entity.getId());
        dto.setBandeira(entity.getBandeira());
        dto.setBandeiraUrl("https://img.carteira.com/" + entity.getBandeira().toLowerCase() + ".svg");
        dto.setNomeTitular(entity.getNomeTitular());
        dto.setValidadeMes(entity.getValidadeMes());
        dto.setValidadeAno(entity.getValidadeAno());
        dto.setTipoCartao(entity.getTipoCartao());
        dto.setStatusAtivo(entity.getAtivo());

        // AGORA usando o método auxiliar do DTO (mais organizado)
        dto.setUltimosDigitos(PaymentMethodDTO.extrairUltimosDigitos(entity.getNumeroCartao()));


        return dto;
    }
}