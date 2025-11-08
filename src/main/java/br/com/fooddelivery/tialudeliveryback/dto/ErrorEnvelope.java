package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorEnvelope {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Error {
        private String codigo;
        private String detalhe;
    }

    private Error erro;

    public static ErrorEnvelope of(String codigo, String detalhe) {
        return ErrorEnvelope.builder()
                .erro(Error.builder().codigo(codigo).detalhe(detalhe).build())
                .build();
    }
}
