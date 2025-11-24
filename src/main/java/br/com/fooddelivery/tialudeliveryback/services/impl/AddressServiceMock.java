package br.com.fooddelivery.tialudeliveryback.services.impl;

import br.com.fooddelivery.tialudeliveryback.dtos.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.SetPrincipalAddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.services.AddressService;
import lombok.AllArgsConstructor;
import lombok.Data;
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
 */
@Service
public class AddressServiceMock implements AddressService {

    // Simula a tabela de banco de dados na memória RAM
    private List<MockEndereco> bancoDeDadosFake = new ArrayList<>();

    public AddressServiceMock() {
        // Dados para teste
        bancoDeDadosFake.add(new MockEndereco(12345L, "Rua das Flores, 100", false));
        bancoDeDadosFake.add(new MockEndereco(67890L, "Av. Paulista, 2000", true));
        bancoDeDadosFake.add(new MockEndereco(11111L, "Rua Torta, 50", false));
    }

    @Override
    public SetPrincipalAddressResponseDTO setAddressAsPrincipal(Long idEndereco, Long idUsuario) {
        // Lógica simulada
        MockEndereco alvo = bancoDeDadosFake.stream()
                .filter(e -> e.getId().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        bancoDeDadosFake.forEach(e -> e.setPrincipal(false));
        alvo.setPrincipal(true);

        return new SetPrincipalAddressResponseDTO("sucesso",
                "O endereço " + idEndereco + " foi definido como principal.", idEndereco);
    }

    @Override
    public List<AddressResponseDTO> listAllAddresses(Long idUsuario) {
        return bancoDeDadosFake.stream()
                .map(e -> new AddressResponseDTO(e.getId(), e.getRua(), e.isPrincipal()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    class MockEndereco {
        private Long id;
        private String rua;
        private boolean principal;
    }
}