package br.com.fooddelivery.tialudeliveryback.domain.service;

import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodDTO;
import br.com.fooddelivery.tialudeliveryback.api.exception.NotFoundException;
import br.com.fooddelivery.tialudeliveryback.api.exception.UnauthorizedException;
import br.com.fooddelivery.tialudeliveryback.api.mapper.PaymentMethodMapper;
import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.domain.repository.PaymentMethodRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentMethodServiceTest {

    @Mock
    private PaymentMethodRepository repository;

    @Mock
    private PaymentMethodMapper mapper;

    @InjectMocks
    private PaymentMethodService service;

    @Test
    @DisplayName("Deve lançar UnauthorizedException quando authenticatedUserId for nulo ou vazio")
    void shouldThrowUnauthorizedWhenUserIdMissing() {
        assertThrows(UnauthorizedException.class, () -> service.getPaymentMethodDetails("MP001", null));
        assertThrows(UnauthorizedException.class, () -> service.getPaymentMethodDetails("MP001", ""));
        assertThrows(UnauthorizedException.class, () -> service.getPaymentMethodDetails("MP001", "   "));
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando meio de pagamento não existir")
    void shouldThrowNotFoundWhenNotExists() {
        when(repository.findById("MP999")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getPaymentMethodDetails("MP999", "user1"));
    }

    @Test
    @DisplayName("Deve retornar DTO quando meio de pagamento pertence ao usuário autenticado")
    void shouldReturnDtoWhenBelongsToUser() {
        // Criamos uma instância anônima que expõe getUserId() para a heurística por reflexão
        PaymentMethod pm = new PaymentMethod();
        pm.setId("MP100");
        pm.setNumeroCartao("1111222233334444");
        pm.setBandeira("Visa");
        pm.setNomeTitular("Teste");
        pm.setValidadeMes(12);
        pm.setValidadeAno(2026);
        pm.setTipoCartao("CREDITO");
        pm.setAtivo(true);

        // Subclasse anônima com getter esperado pela heurística
        PaymentMethod pmWithOwner = new PaymentMethod() {
            public String getUserId() { return "user-abc"; }
        };
        // copiar campos relevantes
        pmWithOwner.setId(pm.getId());
        pmWithOwner.setNumeroCartao(pm.getNumeroCartao());
        pmWithOwner.setBandeira(pm.getBandeira());
        pmWithOwner.setNomeTitular(pm.getNomeTitular());
        pmWithOwner.setValidadeMes(pm.getValidadeMes());
        pmWithOwner.setValidadeAno(pm.getValidadeAno());
        pmWithOwner.setTipoCartao(pm.getTipoCartao());
        pmWithOwner.setAtivo(pm.getAtivo());

        PaymentMethodDTO expectedDto = new PaymentMethodDTO();
        expectedDto.setIdMeioPagamento("MP100");
        expectedDto.setBandeira("Visa");

        when(repository.findById("MP100")).thenReturn(Optional.of(pmWithOwner));
        when(mapper.toDTO(pmWithOwner)).thenReturn(expectedDto);

        PaymentMethodDTO result = service.getPaymentMethodDetails("MP100", "user-abc");

        assertNotNull(result);
        assertEquals("MP100", result.getIdMeioPagamento());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando meio de pagamento existir, mas pertencer a outro usuário")
    void shouldThrowNotFoundWhenBelongsToOtherUser() {
        PaymentMethod pmWithOtherOwner = new PaymentMethod() {
            public String getUserId() { return "owner-xyz"; }
        };
        pmWithOtherOwner.setId("MP200");
        pmWithOtherOwner.setNumeroCartao("9999888877776666");

        when(repository.findById("MP200")).thenReturn(Optional.of(pmWithOtherOwner));

        assertThrows(NotFoundException.class, () -> service.getPaymentMethodDetails("MP200", "user-abc"));
    }
}
