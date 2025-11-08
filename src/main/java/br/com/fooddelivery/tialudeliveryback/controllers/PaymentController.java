package br.com.fooddelivery.tialudeliveryback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class PaymentController {

    @PostMapping("/{idPedido}/payments")
    public ResponseEntity<Object> efetuarPagamento(
            @PathVariable String idPedido,
            @RequestBody Map<String, Object> payload) {
        String idMeioPagamento = (String) payload.get("idMeioPagamento");
        String cvv = (String) payload.get("cvv");
        Integer parcelas = (Integer) payload.getOrDefault("parcelas", 1);

        if (cvv == null || cvv.isEmpty()) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("codigoErro", "VALIDATION_ERROR");
            erro.put("mensagem", "O campo 'cvv' é obrigatório para confirmar o pagamento com o cartão salvo.");
            erro.put("detalhes", new Object[] {
                    Map.of("campo", "cvv", "erro", "O código de segurança (CVV) é obrigatório.")
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }

        String statusAtual = "PENDENTE_PAGAMENTO";
        if (statusAtual.equals("PAGO_AGUARDANDO_ACEITE")) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("codigoErro", "ORDER_STATUS_INVALID");
            erro.put("mensagem", "O pedido já está no status 'PAGO_AGUARDANDO_ACEITE' e não pode ser pago novamente.");
            erro.put("statusPedidoAtual", "PAGO_AGUARDANDO_ACEITE");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
        }

        boolean pagamentoAprovado = Math.random() > 0.3; // só pra testar

        if (pagamentoAprovado) {
            Map<String, Object> sucesso = new HashMap<>();
            sucesso.put("idPedido", idPedido);
            sucesso.put("idMeioPagamento", idMeioPagamento);
            sucesso.put("mensagem",
                    "Pagamento efetuado com sucesso. O pedido foi enviado ao restaurante e aguarda aceitação.");
            sucesso.put("statusPedidoAtual", "PAGO_AGUARDANDO_ACEITE");
            sucesso.put("parcelas", parcelas);

            Map<String, Object> detalhesPagamento = new HashMap<>();
            detalhesPagamento.put("status", "APROVADO");
            detalhesPagamento.put("idTransacao", "txn_xpto7890");
            detalhesPagamento.put("valorTotalCobrado", 65.70);
            detalhesPagamento.put("meioPagamentoUsado", "VISA ************4444");
            sucesso.put("detalhesPagamento", detalhesPagamento);

            return ResponseEntity.ok(sucesso);
        } else {
            Map<String, Object> rejeitado = new HashMap<>();
            rejeitado.put("idPedido", idPedido);
            rejeitado.put("idMeioPagamento", idMeioPagamento);
            rejeitado.put("codigoErro", "PAYMENT_REJECTED");
            rejeitado.put("mensagem",
                    "Pagamento Rejeitado pela instituição financeira. Motivo: Saldo insuficiente ou limite excedido.");
            rejeitado.put("statusPedidoAtual", "PAGAMENTO_FALHOU");
            
            Map<String, Object> detalhesPagamento = new HashMap<>();
            detalhesPagamento.put("status", "REJEITADO");
            detalhesPagamento.put("codigoRejeicao", "51");
            detalhesPagamento.put("acaoSugerida",
                    "Solicite ao usuário que utilize outro cartão ou verifique o limite.");
            rejeitado.put("detalhesPagamento", detalhesPagamento);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rejeitado);
        }
    }
}
