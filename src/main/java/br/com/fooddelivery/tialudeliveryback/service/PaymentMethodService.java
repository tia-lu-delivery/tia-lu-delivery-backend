package br.com.fooddelivery.tialudeliveryback.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository repository;

    public PaymentMethodResponse cadastrar(String idUsuario, PaymentMethodRequest request) {

        if (request.getNumeroCartao() == null || request.getCvv() == null ||
            request.getNomeTitular() == null || request.getCpfTitular() == null ||
            request.getTipoCartao() == null) {
            throw new IllegalArgumentException("Campos obrigatórios ausentes.");
        }

        if (!LuhnValidator.isValid(request.getNumeroCartao())) {
            throw new IllegalArgumentException("Número de cartão inválido.");
        }

        if (!CpfValidator.isValid(request.getCpfTitular())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

       
        if (request.getValidadeMes() < 1 || request.getValidadeMes() > 12) {
             throw new IllegalArgumentException("Mês de validade inválido.");
        }
        
        YearMonth validade = YearMonth.of(request.getValidadeAno(), request.getValidadeMes());
        if (validade.isBefore(YearMonth.now())) {
            throw new IllegalArgumentException("Cartão vencido.");
        }

        String encryptedCard = EncryptionUtil.encrypt(request.getNumeroCartao());
        Optional<PaymentMethod> existing = repository.findByIdUsuarioAndNumeroCartaoCriptografado(idUsuario, encryptedCard);
        if (existing.isPresent()) {
            throw new IllegalStateException("DUPLICATE_CARD");
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

        return PaymentMethodResponse.builder()
                .idMeioPagamento(saved.getIdMeioPagamento())
                .mensagem("Cartão cadastrado com sucesso. Dados armazenados.")
                .detalhesCartao(PaymentMethodResponse.DetalhesCartao.builder()
                        .tipo(saved.getTipoCartao())
                        .bandeira(bandeira)
                        .ultimosDigitos(ultimosDigitos)
                        .nomeExibicao(bandeira + " ************" + ultimosDigitos)
                        .build())
                .build();
    }

    private String inferirBandeira(String numero) {
        if (numero.startsWith("4")) return "VISA";
        if (numero.startsWith("5")) return "MASTERCARD";
        if (numero.startsWith("34") || numero.startsWith("37")) return "AMEX";
        return "DESCONHECIDA";
    }
}