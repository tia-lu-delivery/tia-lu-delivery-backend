package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class PagamentoResponseDTO {
    private String idPedido;
    private String mensagem;
    private String statusPedidoAtual;
    private DetalhesPagamento detalhesPagamento;

    @Data
    @Builder
    public static class DetalhesPagamento {
        private String status;
        private String idTransacao;
        private BigDecimal valorTotalCobrado;
        private String meioPagamentoUsado;
        private String codigoRejeicao;
        private String acaoSugerida;
    }
}