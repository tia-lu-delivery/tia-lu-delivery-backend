package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    
    private ErrorDetail erro;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorDetail {
        private String codigo;
        private String detalhe;
    }
    
    public static ErrorResponseDTO create(String codigo, String detalhe) {
        return new ErrorResponseDTO(new ErrorDetail(codigo, detalhe));
    }
}
