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
        // Supondo que você tenha uma lógica de URL base para bandeiras, ou venha do banco
        // Exemplo fixo ou vindo do banco:
        dto.setBandeiraUrl("https://img.carteira.com/" + entity.getBandeira().toLowerCase() + ".svg"); 
        
        dto.setNomeTitular(entity.getNomeTitular());
        dto.setValidadeMes(entity.getValidadeMes());
        dto.setValidadeAno(entity.getValidadeAno());
        dto.setTipoCartao(entity.getTipoCartao()); // Ex: "CREDITO"
        dto.setStatusAtivo(entity.getAtivo());

        // Lógica CA-004: Extrair apenas os últimos 4 dígitos
        if (entity.getNumeroCartao() != null && entity.getNumeroCartao().length() >= 4) {
            String numeroCompleto = entity.getNumeroCartao();
            String ultimos4 = numeroCompleto.substring(numeroCompleto.length() - 4);
            dto.setUltimosDigitos(ultimos4);
        } else {
            dto.setUltimosDigitos("****"); // Fallback caso venha nulo
        }

        return dto;
    }
}