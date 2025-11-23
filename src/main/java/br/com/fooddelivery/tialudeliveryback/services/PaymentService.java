package br.com.fooddelivery.tialudeliveryback.services;

import br.com.fooddelivery.tialudeliveryback.dtos.PaymentRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.PaymentResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entities.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.repositories.PaymentRepository;
import br.com.fooddelivery.tialudeliveryback.exceptions.DuplicateCardException;
import br.com.fooddelivery.tialudeliveryback.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    @SuppressWarnings("null")
    public PaymentResponseDTO cadastrar(String idUsuario, PaymentRequestDTO request) {

        // Obter o número do cartão limpo através do DTO
        String numeroCartaoLimpo = request.getNumeroCartaoLimpo();

        // Verificar se o cartão já está cadastrado (CA 1.7 - Verificação de duplicatas)
        String encryptedCard = EncryptionUtil.encrypt(numeroCartaoLimpo);
        Optional<PaymentMethod> existing = repository.findByIdUsuarioAndNumeroCartaoCriptografado(idUsuario,
                encryptedCard);
        if (existing.isPresent()) {
            throw new DuplicateCardException(
                    "Este cartão já está cadastrado em sua carteira.",
                    "O cartão já está pronto para uso. Utilize o '" + existing.get().getIdMeioPagamento() + "'.");
        }

        String ultimosDigitos = numeroCartaoLimpo.substring(numeroCartaoLimpo.length() - 4);
        String bandeira = inferirBandeira(numeroCartaoLimpo);

        PaymentMethod saved = repository.save(PaymentMethod.builder()

                .numeroCartaoCriptografado(encryptedCard)
                .validadeMes(request.getValidadeMes())
                .validadeAno(request.getValidadeAno())
                .cvvCriptografado(EncryptionUtil.encrypt(request.getCvv()))
                .nomeTitular(request.getNomeTitular())
                .cpfTitular(request.getCpfTitular())
                .tipoCartao(request.getTipoCartao())
                .bandeira(bandeira)
                .ultimosDigitos(ultimosDigitos)
                .idUsuario(idUsuario)
                .build());

        return PaymentResponseDTO.builder()
                .idMeioPagamento(saved.getIdMeioPagamento())
                .mensagem("Cartão cadastrado com sucesso. Dados armazenados.")
                .detalhesCartao(PaymentResponseDTO.DetalhesCartao.builder()
                        .tipo(saved.getTipoCartao().toString())
                        .bandeira(bandeira)
                        .ultimosDigitos(ultimosDigitos)
                        .nomeExibicao(bandeira + " ************" + ultimosDigitos)
                        .build())
                .build();
    }

    private String inferirBandeira(String numeroLimpo) {
        
        // VISA: inicia com 4 (13, 16 ou 19 dígitos)
        if (numeroLimpo.startsWith("4")) {
            return "VISA";
        }
        
        // MASTERCARD: inicia com 5 (5100-5599) ou 2 (2221-2720)
        if (numeroLimpo.startsWith("5")) {
            int prefixo = Integer.parseInt(numeroLimpo.substring(0, 4));
            if (prefixo >= 5100 && prefixo <= 5599) {
                return "MASTERCARD";
            }
        }
        if (numeroLimpo.length() >= 4) {
            int prefixo = Integer.parseInt(numeroLimpo.substring(0, 4));
            if (prefixo >= 2221 && prefixo <= 2720) {
                return "MASTERCARD";
            }
        }
        
        // AMERICAN EXPRESS: inicia com 34 ou 37 (15 dígitos)
        if (numeroLimpo.startsWith("34") || numeroLimpo.startsWith("37")) {
            return "AMEX";
        }
        
        // DINERS CLUB: inicia com 30, 36, 38 ou 300-305 (14 dígitos)
        if (numeroLimpo.startsWith("30") || numeroLimpo.startsWith("36") || numeroLimpo.startsWith("38")) {
            return "DINERS";
        }
        
        // DISCOVER: inicia com 6011, 622126-622925, 644-649, 65
        if (numeroLimpo.startsWith("6011") || numeroLimpo.startsWith("65")) {
            return "DISCOVER";
        }
        if (numeroLimpo.length() >= 6) {
            int prefixo6 = Integer.parseInt(numeroLimpo.substring(0, 6));
            if (prefixo6 >= 622126 && prefixo6 <= 622925) {
                return "DISCOVER";
            }
        }
        if (numeroLimpo.length() >= 3) {
            int prefixo3 = Integer.parseInt(numeroLimpo.substring(0, 3));
            if (prefixo3 >= 644 && prefixo3 <= 649) {
                return "DISCOVER";
            }
        }
        
        // TICKET: vale alimentação - prefixos comuns dos cartões Ticket
        if (numeroLimpo.startsWith("506699") || numeroLimpo.startsWith("506770") || 
            numeroLimpo.startsWith("506771") || numeroLimpo.startsWith("506772") ||
            numeroLimpo.startsWith("506773") || numeroLimpo.startsWith("506774") ||
            numeroLimpo.startsWith("506775") || numeroLimpo.startsWith("506776") ||
            numeroLimpo.startsWith("506777") || numeroLimpo.startsWith("506778")) {
            return "TICKET";
        }
        
        // VR (VISA VALE): vale alimentação e refeição
        if (numeroLimpo.startsWith("627416") || numeroLimpo.startsWith("606014") ||
            numeroLimpo.startsWith("506017") || numeroLimpo.startsWith("506018") ||
            numeroLimpo.startsWith("506019") || numeroLimpo.startsWith("506020") ||
            numeroLimpo.startsWith("506021") || numeroLimpo.startsWith("506022") ||
            numeroLimpo.startsWith("506023") || numeroLimpo.startsWith("506024") ||
            numeroLimpo.startsWith("506025") || numeroLimpo.startsWith("506026") ||
            numeroLimpo.startsWith("506027")) {
            return "VR";
        }
        
        // ALELO: vale alimentação e refeição
        if (numeroLimpo.startsWith("506707") || numeroLimpo.startsWith("506708") ||
            numeroLimpo.startsWith("506709") || numeroLimpo.startsWith("506710") ||
            numeroLimpo.startsWith("506711") || numeroLimpo.startsWith("506712") ||
            numeroLimpo.startsWith("506713") || numeroLimpo.startsWith("506714") ||
            numeroLimpo.startsWith("506715") || numeroLimpo.startsWith("506716") ||
            numeroLimpo.startsWith("506717") || numeroLimpo.startsWith("506718") ||
            numeroLimpo.startsWith("506719") || numeroLimpo.startsWith("506720")) {
            return "ALELO";
        }
        
        // SODEXO: vale alimentação e refeição
        if (numeroLimpo.startsWith("506738") || numeroLimpo.startsWith("506739") ||
            numeroLimpo.startsWith("506740") || numeroLimpo.startsWith("506741") ||
            numeroLimpo.startsWith("506742") || numeroLimpo.startsWith("506743") ||
            numeroLimpo.startsWith("506744") || numeroLimpo.startsWith("506745") ||
            numeroLimpo.startsWith("506746") || numeroLimpo.startsWith("506747") ||
            numeroLimpo.startsWith("506748")) {
            return "SODEXO";
        }
        
        // BEN VISA VALE: vale alimentação e refeição
        if (numeroLimpo.startsWith("506062") || numeroLimpo.startsWith("506063") ||
            numeroLimpo.startsWith("506064") || numeroLimpo.startsWith("506065") ||
            numeroLimpo.startsWith("506066") || numeroLimpo.startsWith("506067") ||
            numeroLimpo.startsWith("506068")) {
            return "BEN";
        }
        
        // GREENCARD: vale alimentação
        if (numeroLimpo.startsWith("506285") || numeroLimpo.startsWith("506286") ||
            numeroLimpo.startsWith("506287") || numeroLimpo.startsWith("506288") ||
            numeroLimpo.startsWith("506289")) {
            return "GREENCARD";
        }
        
        // PLANVALE: vale alimentação e refeição
        if (numeroLimpo.startsWith("606680") || numeroLimpo.startsWith("606681") ||
            numeroLimpo.startsWith("606682") || numeroLimpo.startsWith("606683")) {
            return "PLANVALE";
        }
        
        // HIPERCARD: prefixos brasileiros
        if (numeroLimpo.startsWith("606282") || numeroLimpo.startsWith("637095") || 
            numeroLimpo.startsWith("637568") || numeroLimpo.startsWith("637599") ||
            numeroLimpo.startsWith("637609") || numeroLimpo.startsWith("637612")) {
            return "HIPERCARD";
        }
        
        // JCB: inicia com 35 (16 dígitos)
        if (numeroLimpo.startsWith("35")) {
            return "JCB";
        }
        
        return "DESCONHECIDA";
    }
}