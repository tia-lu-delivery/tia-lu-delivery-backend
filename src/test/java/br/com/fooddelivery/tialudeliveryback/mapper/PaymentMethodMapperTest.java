package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodDTO;
import br.com.fooddelivery.tialudeliveryback.api.mapper.PaymentMethodMapper;
import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodMapperTest {

    private final PaymentMethodMapper mapper = new PaymentMethodMapper();

    @Test
    @DisplayName("Deve mapear Entity para DTO corretamente e ocultar dados sensíveis (CA-004)")
    void deveConverterEntityParaDtoComSeguranca() {
        // 1. Cenário (Dados sensíveis no banco)
        PaymentMethod entity = new PaymentMethod();
        entity.setId("MP001");
        entity.setNumeroCartao("1234567890124321"); // Número completo
        entity.setCvv("999"); // Dado sensível
        entity.setBandeira("Visa");
        entity.setNomeTitular("JOAO DA SILVA");
        entity.setValidadeMes(12);
        entity.setValidadeAno(2028);
        entity.setTipoCartao("CREDITO");
        entity.setAtivo(true);

        // 2. Execução
        PaymentMethodDTO dto = mapper.toDTO(entity);

        // 3. Validação
        assertNotNull(dto);
        assertEquals("MP001", dto.getIdMeioPagamento());
        
        // Validação CA-004: Deve retornar apenas os ultimos 4 digitos
        assertEquals("4321", dto.getUltimosDigitos());
        assertNotEquals("1234567890124321", dto.getUltimosDigitos());
        
        // Outros campos
        assertEquals("Visa", dto.getBandeira());
        assertEquals("JOAO DA SILVA", dto.getNomeTitular());
        assertTrue(dto.getStatusAtivo());
    }
}