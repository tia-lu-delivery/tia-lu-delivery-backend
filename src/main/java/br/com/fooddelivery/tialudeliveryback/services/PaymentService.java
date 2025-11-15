package br.com.fooddelivery.tialudeliveryback.services;

import br.com.fooddelivery.tialudeliveryback.dtos.PaymentRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.PaymentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
        // Classe de teste

        public PaymentResponseDTO addCard(Long idUsuario, PaymentRequestDTO request) {

                // Gera ID fictício do meio de pagamento
                String idMeioPagamento = UUID.randomUUID().toString();

                // Obtém últimos 4 dígitos
                String ultimosDigitos = request.getNumeroCartao()
                                .substring(request.getNumeroCartao().length() - 4);

                // Cria detalhes simulados
                PaymentResponseDTO.DetalhesCartao detalhes = new PaymentResponseDTO.DetalhesCartao(
                                request.getTipoCartao(),
                                "VISA",
                                ultimosDigitos,
                                request.getNomeTitular());

                return new PaymentResponseDTO(
                                idMeioPagamento,
                                "Cartão cadastrado com sucesso!",
                                detalhes);
        }
}
