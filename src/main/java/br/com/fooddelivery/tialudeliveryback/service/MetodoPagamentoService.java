package br.com.fooddelivery.tialudeliveryback.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MetodoPagamentoService {

    public Map<String, Object> efetuarPagamento(String idPedido, String idMeioPagamento, String cvv, Integer parcelas) {
        Map<String, Object> resposta = new HashMap<>();

        if (idPedido == null || idPedido.isEmpty() || idMeioPagamento == null || idMeioPagamento.isEmpty()) {
            resposta.put("codigoErro", "VALIDATION_ERROR");
            resposta.put("mensagem", "Os campos 'idPedido' e 'idMeioPagamento' são obrigatórios.");
            return resposta;
        }

        if (cvv == null || cvv.isEmpty()) {
            resposta.put("codigoErro", "VALIDATION_ERROR");
            resposta.put("mensagem", "O campo 'cvv' é obrigatório para confirmar o pagamento com o cartão salvo.");
            return resposta;
        }

        boolean estoqueDisponivel = true;

        if (!estoqueDisponivel) {
            resposta.put("codigoErro", "OUT_OF_STOCK");
            resposta.put("mensagem", "Itens esgotados. O pagamento não foi processado.");
            return resposta;
        }

        boolean pagamentoAprovado = new Random().nextBoolean();

        if (pagamentoAprovado) {
            resposta.put("idPedido", idPedido);
            resposta.put("mensagem", "Pagamento efetuado com sucesso. O pedido foi enviado ao restaurante e aguarda aceitação.");
            resposta.put("statusPedidoAtual", "PAGO_AGUARDANDO_ACEITE");

            Map<String, Object> detalhesPagamento = new HashMap<>();
            detalhesPagamento.put("status", "APROVADO");
            detalhesPagamento.put("idTransacao", "txn_" + System.currentTimeMillis());
            detalhesPagamento.put("valorTotalCobrado", 65.70);
            detalhesPagamento.put("meioPagamentoUsado", "VISA **** **** **** 4444");

            resposta.put("detalhesPagamento", detalhesPagamento);

        } else {
            resposta.put("idPedido", idPedido);
            resposta.put("codigoErro", "PAYMENT_REJECTED");
            resposta.put("mensagem", "Pagamento rejeitado pela instituição financeira (mock). Motivo: saldo insuficiente ou limite excedido.");
            resposta.put("statusPedidoAtual", "PAGAMENTO_FALHOU");
        }

        return resposta;
    }
}
