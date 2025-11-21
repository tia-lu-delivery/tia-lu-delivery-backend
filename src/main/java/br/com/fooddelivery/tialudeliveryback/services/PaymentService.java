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

        // Verificar se o cartão já está cadastrado (CA 1.7 - Verificação de duplicatas)
        String encryptedCard = EncryptionUtil.encrypt(request.getNumeroCartao());
        Optional<PaymentMethod> existing = repository.findByIdUsuarioAndNumeroCartaoCriptografado(idUsuario,
                encryptedCard);
        if (existing.isPresent()) {
            throw new DuplicateCardException(
                    "Este cartão já está cadastrado em sua carteira.",
                    "O cartão já está pronto para uso. Utilize o '" + existing.get().getIdMeioPagamento() + "'.");
        }

        String ultimosDigitos = request.getNumeroCartao().substring(request.getNumeroCartao().length() - 4);
        String bandeira = inferirBandeira(request.getNumeroCartao());

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

    private String inferirBandeira(String numero) {
        if (numero.startsWith("4"))
            return "VISA";
        if (numero.startsWith("5"))
            return "MASTERCARD";
        if (numero.startsWith("3"))
            return "AMEX";
        return "DESCONHECIDA";
    }
}