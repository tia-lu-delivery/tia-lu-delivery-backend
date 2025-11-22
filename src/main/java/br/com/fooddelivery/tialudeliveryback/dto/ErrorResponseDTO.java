package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    @JsonProperty("erro")
    private ErrorDetail erro;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDetail {
        @JsonProperty("codigo")
        private String codigo;

        @JsonProperty("detalhe")
        private String detalhe;
    }

    public static ErrorResponseDTO of(String codigo, String detalhe) {
        return ErrorResponseDTO.builder()
                .erro(ErrorDetail.builder()
                        .codigo(codigo)
                        .detalhe(detalhe)
                        .build())
                .build();
    }
}