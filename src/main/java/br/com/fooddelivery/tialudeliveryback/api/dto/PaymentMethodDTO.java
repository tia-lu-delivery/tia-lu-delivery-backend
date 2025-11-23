package br.com.fooddelivery.tialudeliveryback.api.dto;

import lombok.Data;

@Data
public class PaymentMethodDTO {
    private String idMeioPagamento;
    private String bandeira;
    private String bandeiraUrl;
    private String nomeTitular;
    private Integer validadeMes;
    private Integer validadeAno;
    private String tipoCartao;
    private Boolean statusAtivo;
    private String ultimosDigitos;
    
    // --- MÉTODOS AUXILIARES ADICIONADOS ---
    
    /**
     * Extrai os últimos 4 dígitos do número do cartão
     * Compatível com a lógica existente no Mapper
     */
    public static String extrairUltimosDigitos(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.length() < 4) {
            return "****";
        }
        return numeroCartao.substring(numeroCartao.length() - 4);
    }
    
    /**
     * Formata a validade como "MM/AAAA" - OPICIONAL (pode ser usado no frontend)
     */
    public String getValidadeFormatada() {
        if (validadeMes == null || validadeAno == null) {
            return null;
        }
        return String.format("%02d/%d", validadeMes, validadeAno);
    }
    
    /**
     * Método conveniente para verificar se está ativo
     */
    public boolean isAtivo() {
        return Boolean.TRUE.equals(statusAtivo);
    }
}