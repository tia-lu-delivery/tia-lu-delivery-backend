package br.com.fooddelivery.tialudeliveryback.api.controller;

import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodDTO;
import br.com.fooddelivery.tialudeliveryback.api.exception.NotFoundException;
import br.com.fooddelivery.tialudeliveryback.api.exception.UnauthorizedException;
import br.com.fooddelivery.tialudeliveryback.domain.service.PaymentMethodService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável por expor o endpoint de consulta de meio de pagamento.
 *
 * Atende à especificação:
 *
 * - Sucesso (200):
 *   {
 *     "id_meio_pagamento": "MP001",
 *     "bandeira": "Visa",
 *     "bandeira_url": "https://img.carteira.com/visa.svg",
 *     "ultimos_digitos": "4321",
 *     "nome_titular": "JOAO DA SILVA",
 *     "validade_mes": 12,
 *     "validade_ano": 2028,
 *     "tipo_cartao": "CREDITO",
 *     "status_ativo": true
 *   }
 *
 * - Erro 404:
 *   {
 *     "erro": {
 *       "codigo": "MEIO_PAGAMENTO_NAO_ENCONTRADO",
 *       "detalhe": "O meio de pagamento com ID 'MP999' não foi encontrado ou não pertence a este usuário."
 *     }
 *   }
 *
 * - Erro 401:
 *   {
 *     "erro": {
 *       "codigo": "NAO_AUTORIZADO",
 *       "detalhe": "Token de autenticação ausente ou inválido."
 *     }
 *   }
 */
@RestController
@RequestMapping("/meios-pagamento")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    /**
     * GET /meios-pagamento/{idMeioPagamento}
     *
     * A autenticação do usuário está representada aqui por um header simples "X-User-Id",
     * apenas para fins de exemplo. No mundo real isso viria do token/JWT ou do SecurityContext.
     */
    @GetMapping("/{idMeioPagamento}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethod(
            @PathVariable String idMeioPagamento,
            @RequestHeader(value = "X-User-Id", required = false) String authenticatedUserId) {

        PaymentMethodDTO dto =
                paymentMethodService.getPaymentMethodDetails(idMeioPagamento, authenticatedUserId);

        return ResponseEntity.ok(dto);
    }

    // ---------- Mapeamento dos erros para o JSON especificado ----------

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEnvelope> handleNotFound(NotFoundException ex) {
        ErrorEnvelope body = new ErrorEnvelope(new ErrorBody(ex.getCodigo(), ex.getDetalhe()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorEnvelope> handleUnauthorized(UnauthorizedException ex) {
        ErrorEnvelope body = new ErrorEnvelope(new ErrorBody(ex.getCodigo(), ex.getDetalhe()));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    // ---------- DTO interno para o corpo de erro: { "erro": { "codigo", "detalhe" } } ----------

    @Data
    @AllArgsConstructor
    static class ErrorEnvelope {
        private ErrorBody erro;
    }

    @Data
    @AllArgsConstructor
    static class ErrorBody {
        private String codigo;
        private String detalhe;
    }
}
