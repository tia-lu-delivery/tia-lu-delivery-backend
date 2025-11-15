package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.PagamentoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PagamentoResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.Pagamento;
import br.com.fooddelivery.tialudeliveryback.entity.StatusPagamento;
import br.com.fooddelivery.tialudeliveryback.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoResponseDTO efetuarPagamento(String idPedido, PagamentoRequestDTO requestDTO) {
        if (pagamentoRepository.existsByIdPedido(idPedido)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "ORDER_STATUS_INVALID: O pedido já está no status 'PAGO_AGUARDANDO_ACEITE' e não pode ser pago novamente.");
        }
        boolean pagamentoAprovado = new Random().nextBoolean();
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPedido(idPedido);
        pagamento.setIdMeioPagamento(requestDTO.getIdMeioPagamento());
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setIdTransacao("txn_" + System.currentTimeMillis());
        pagamento.setParcelas(requestDTO.getParcelas());
        pagamento.setValorTotalCobrado(BigDecimal.valueOf(65.70)); // Valor simulado

        if (pagamentoAprovado) {
            pagamento.setStatusPagamento(StatusPagamento.APROVADO);

            boolean envioSucesso = new Random().nextBoolean();
            pagamento.setErroEnvioEstabelecimento(!envioSucesso);

            Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

            return construirResponseSucesso(idPedido, pagamentoSalvo);

        } else {
            pagamento.setStatusPagamento(StatusPagamento.REJEITADO);
            pagamento.setCodigoRejeicao("51");

            Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "PAYMENT_REJECTED: Pagamento Rejeitado pela instituição financeira. Motivo: Saldo insuficiente ou limite excedido.");
        }
    }

    private PagamentoResponseDTO construirResponseSucesso(String idPedido, Pagamento pagamento) {
        return PagamentoResponseDTO.builder()
                .idPedido(idPedido)
                .mensagem("Pagamento efetuado com sucesso. O pedido foi enviado ao restaurante e aguarda aceitação.")
                .statusPedidoAtual("PAGO_AGUARDANDO_ACEITE")
                .detalhesPagamento(PagamentoResponseDTO.DetalhesPagamento.builder()
                        .status(pagamento.getStatusPagamento().name())
                        .idTransacao(pagamento.getIdTransacao())
                        .valorTotalCobrado(pagamento.getValorTotalCobrado())
                        .meioPagamentoUsado("VISA **** **** **** 4444")
                        .build())
                .build();
    }
}