package br.com.fooddelivery.tialudeliveryback.dto;

import org.junit.jupiter.api.Test;

import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodDTO;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodDTOTest {

    @Test
    @DisplayName("Deve extrair últimos 4 dígitos de número de cartão válido")
    void testExtrairUltimosDigitos_NumeroValido() {
        // Arrange & Act
        String resultado = PaymentMethodDTO.extrairUltimosDigitos("1234567890123456");
        
        // Assert
        assertEquals("3456", resultado);
    }

    @Test
    @DisplayName("Deve retornar **** para número de cartão nulo")
    void testExtrairUltimosDigitos_NumeroNulo() {
        // Arrange & Act
        String resultado = PaymentMethodDTO.extrairUltimosDigitos(null);
        
        // Assert
        assertEquals("****", resultado);
    }

    @Test
    @DisplayName("Deve retornar **** para número de cartão vazio")
    void testExtrairUltimosDigitos_NumeroVazio() {
        // Arrange & Act
        String resultado = PaymentMethodDTO.extrairUltimosDigitos("");
        
        // Assert
        assertEquals("****", resultado);
    }

    @Test
    @DisplayName("Deve retornar **** para número de cartão com menos de 4 dígitos")
    void testExtrairUltimosDigitos_NumeroCurto() {
        // Arrange & Act
        String resultado = PaymentMethodDTO.extrairUltimosDigitos("123");
        
        // Assert
        assertEquals("****", resultado);
    }

    @Test
    @DisplayName("Deve retornar últimos 4 dígitos para número exatamente com 4 dígitos")
    void testExtrairUltimosDigitos_NumeroExato4Digitos() {
        // Arrange & Act
        String resultado = PaymentMethodDTO.extrairUltimosDigitos("1234");
        
        // Assert
        assertEquals("1234", resultado);
    }

    @Test
    @DisplayName("Deve formatar validade corretamente com mês de um dígito")
    void testGetValidadeFormatada_MesUmDigito() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setValidadeMes(5);
        dto.setValidadeAno(2025);
        
        // Act
        String validadeFormatada = dto.getValidadeFormatada();
        
        // Assert
        assertEquals("05/2025", validadeFormatada);
    }

    @Test
    @DisplayName("Deve formatar validade corretamente com mês de dois dígitos")
    void testGetValidadeFormatada_MesDoisDigitos() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setValidadeMes(12);
        dto.setValidadeAno(2026);
        
        // Act
        String validadeFormatada = dto.getValidadeFormatada();
        
        // Assert
        assertEquals("12/2026", validadeFormatada);
    }

    @Test
    @DisplayName("Deve retornar null quando mês for nulo")
    void testGetValidadeFormatada_MesNulo() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setValidadeMes(null);
        dto.setValidadeAno(2025);
        
        // Act
        String validadeFormatada = dto.getValidadeFormatada();
        
        // Assert
        assertNull(validadeFormatada);
    }

    @Test
    @DisplayName("Deve retornar null quando ano for nulo")
    void testGetValidadeFormatada_AnoNulo() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setValidadeMes(12);
        dto.setValidadeAno(null);
        
        // Act
        String validadeFormatada = dto.getValidadeFormatada();
        
        // Assert
        assertNull(validadeFormatada);
    }

    @Test
    @DisplayName("Deve retornar true para status ativo")
    void testIsAtivo_StatusTrue() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setStatusAtivo(true);
        
        // Act & Assert
        assertTrue(dto.isAtivo());
    }

    @Test
    @DisplayName("Deve retornar false para status inativo")
    void testIsAtivo_StatusFalse() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setStatusAtivo(false);
        
        // Act & Assert
        assertFalse(dto.isAtivo());
    }

    @Test
    @DisplayName("Deve retornar false para status nulo")
    void testIsAtivo_StatusNulo() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setStatusAtivo(null);
        
        // Act & Assert
        assertFalse(dto.isAtivo());
    }

    @Test
    @DisplayName("Deve funcionar corretamente com Lombok - getters e setters")
    void testLombokGettersAndSetters() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        
        // Act
        dto.setIdMeioPagamento("MP001");
        dto.setBandeira("Visa");
        dto.setBandeiraUrl("https://img.carteira.com/visa.svg");
        dto.setNomeTitular("João Silva");
        dto.setValidadeMes(12);
        dto.setValidadeAno(2025);
        dto.setTipoCartao("CREDITO");
        dto.setStatusAtivo(true);
        dto.setUltimosDigitos("1234");
        
        // Assert
        assertEquals("MP001", dto.getIdMeioPagamento());
        assertEquals("Visa", dto.getBandeira());
        assertEquals("https://img.carteira.com/visa.svg", dto.getBandeiraUrl());
        assertEquals("João Silva", dto.getNomeTitular());
        assertEquals(12, dto.getValidadeMes());
        assertEquals(2025, dto.getValidadeAno());
        assertEquals("CREDITO", dto.getTipoCartao());
        assertTrue(dto.getStatusAtivo());
        assertEquals("1234", dto.getUltimosDigitos());
    }

    @Test
    @DisplayName("Deve implementar equals e hashCode corretamente")
    void testEqualsAndHashCode() {
        // Arrange
        PaymentMethodDTO dto1 = new PaymentMethodDTO();
        dto1.setIdMeioPagamento("MP001");
        dto1.setBandeira("Visa");
        
        PaymentMethodDTO dto2 = new PaymentMethodDTO();
        dto2.setIdMeioPagamento("MP001");
        dto2.setBandeira("Visa");
        
        PaymentMethodDTO dto3 = new PaymentMethodDTO();
        dto3.setIdMeioPagamento("MP002");
        dto3.setBandeira("Mastercard");
        
        // Assert
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
    }

    @Test
    @DisplayName("Deve implementar toString corretamente")
    void testToString() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setIdMeioPagamento("MP001");
        dto.setBandeira("Visa");
        dto.setUltimosDigitos("1234");
        
        // Act
        String toStringResult = dto.toString();
        
        // Assert
        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("MP001"));
        assertTrue(toStringResult.contains("Visa"));
        assertTrue(toStringResult.contains("1234"));
    }

    @Test
    @DisplayName("Deve funcionar integração completa dos métodos auxiliares")
    void testIntegracaoMetodosAuxiliares() {
        // Arrange
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setValidadeMes(3);
        dto.setValidadeAno(2027);
        dto.setStatusAtivo(true);
        
        // Act & Assert - Testa todos os métodos auxiliares juntos
        assertEquals("03/2027", dto.getValidadeFormatada());
        assertTrue(dto.isAtivo());
        assertEquals("5678", PaymentMethodDTO.extrairUltimosDigitos("12345678"));
    }
}