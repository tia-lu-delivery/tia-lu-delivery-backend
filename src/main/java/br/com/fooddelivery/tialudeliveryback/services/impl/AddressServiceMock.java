package br.com.fooddelivery.tialudeliveryback.services.impl;

import br.com.fooddelivery.tialudeliveryback.dtos.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.SetPrincipalAddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exceptions.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.repositories.AddressRepository;
import br.com.fooddelivery.tialudeliveryback.services.AddressService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ⚠️ ARQUIVO DE MOCK (TEMPORÁRIO) ⚠️
 * ---------------------------------------------------------
 * Este arquivo simula o comportamento do Banco de Dados em memória.
 *
 * MOTIVO: Permitir o desenvolvimento do Controller (#160) enquanto
 * as features da equipe DWOO-012 - Cadastro de Endereço (ROMA)
 * não está pronta.
 *
 * AÇÃO FUTURA: DELETAR este arquivo antes de fazer o merge com as
 * demais funcionalidades.
 *
 * ESTRATÉGIA:
 * - Ativada como bean padrão quando AddressRepository não estiver disponível
 * - Em testes, pode ser sobrescrita pela configuração TestAddressServiceConfiguration
 * - Em produção com DB, será substituída por AddressServiceImpl
 */
@Service
@ConditionalOnMissingBean(AddressRepository.class)
public class AddressServiceMock implements AddressService {

    // Simula a tabela de banco de dados na memória RAM
    private List<MockEndereco> bancoDeDadosFake = new ArrayList<>();

    public AddressServiceMock() {
        // Dados para teste - Usuário 1 possui endereços
        bancoDeDadosFake.add(new MockEndereco(12345L, 1L, "Rua das Flores, 100", false));
        bancoDeDadosFake.add(new MockEndereco(67890L, 1L, "Av. Paulista, 2000", true));
        bancoDeDadosFake.add(new MockEndereco(11111L, 1L, "Rua Torta, 50", false));
        
        // Dados para teste - Usuário 2 possui endereço diferente (para CA-002)
        bancoDeDadosFake.add(new MockEndereco(99999L, 2L, "Rua do Outro Usuário, 999", false));
    }

    @Override
    public SetPrincipalAddressResponseDTO setAddressAsPrincipal(Long idEndereco, Long idUsuario)
            throws AddressNotFoundException {
        // Valida se o endereço existe E pertence ao usuário (CA-002 multi-tenant)
        MockEndereco alvo = bancoDeDadosFake.stream()
                .filter(e -> e.getId().equals(idEndereco) && e.getUserId().equals(idUsuario))
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException(
                        "O endereço especificado não existe ou não pertence ao usuário."));

        // Desativa endereços principais do usuário (CA-004)
        bancoDeDadosFake.stream()
                .filter(e -> e.getUserId().equals(idUsuario))
                .forEach(e -> e.setPrincipal(false));
        
        // Ativa o endereço selecionado como principal
        alvo.setPrincipal(true);

        return new SetPrincipalAddressResponseDTO("sucesso",
                "O endereço " + idEndereco + " foi definido como principal.", idEndereco);
    }

    @Override
    public List<AddressResponseDTO> listAllAddresses(Long idUsuario) {
        return bancoDeDadosFake.stream()
                .filter(e -> e.getUserId().equals(idUsuario))
                .map(e -> new AddressResponseDTO(e.getId(), e.getRua(), e.isPrincipal()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    class MockEndereco {
        private Long id;
        private Long userId;
        private String rua;
        private boolean principal;
    }
}