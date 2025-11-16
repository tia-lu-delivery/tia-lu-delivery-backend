package br.com.fooddelivery.tialudeliveryback.services;

import br.com.fooddelivery.tialudeliveryback.dtos.PaymentRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.PaymentResponseDTO;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PaymentService {

        public PaymentResponseDTO addCard(Long idUsuario, PaymentRequestDTO request) {

                // Gera ID fictício do meio de pagamento no padrão mpidXXXXXXXXX
                Random random = new Random();
                int randomNumber = 100_000_000 + random.nextInt(900_000_000); // 9 dígitos
                String idMeioPagamento = "mpid" + randomNumber;

                // Obtém últimos 4 dígitos
                String ultimosDigitos = request.getNumeroCartao()
                                .substring(request.getNumeroCartao().length() - 4);

                // Gera nome exibido: "VISA ************4444"
                String nomeExibicao = "VISA " + "*".repeat(12) + ultimosDigitos;

                // Cria detalhes simulados
                PaymentResponseDTO.DetalhesCartao detalhes = new PaymentResponseDTO.DetalhesCartao(
                                request.getTipoCartao().name(), // tipo vindo do DTO (CREDITO / DEBITO)
                                "VISA", // bandeira simulada
                                ultimosDigitos,
                                nomeExibicao);

                return new PaymentResponseDTO(
                                idMeioPagamento,
                                "Cartão cadastrado com sucesso. Dados armazenados.",
                                detalhes);
        }
}