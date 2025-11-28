package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.UpdatePaymentMethodRequest;
import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.enum_.TipoCartao;
import br.com.fooddelivery.tialudeliveryback.mapper.PaymentMethodMapper;
import br.com.fooddelivery.tialudeliveryback.repository.PaymentMethodRepository;
import br.com.fooddelivery.tialudeliveryback.util.CreditCardValidator;
import br.com.fooddelivery.tialudeliveryback.util.SecurityUtils;
import br.com.fooddelivery.tialudeliveryback.util.TokenizerUtils;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    public PaymentMethodResponseDTO updatePaymentMethod(String id, UpdatePaymentMethodRequest req) {

        Long userId = SecurityUtils.getAuthenticatedUserId();

        PaymentMethod entity = repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("MEIO_PAGAMENTO_NAO_PERTENCE_AO_USUARIO"));

        // VALIDAR NUMERO DO CARTAO (Luhn)
        if (!CreditCardValidator.isValid(req.getNumeroCartao())) {
            throw new RuntimeException("NUMERO_CARTAO_INVALIDO");
        }

        // VALIDAR CVV (não será persistido)
        if (!CreditCardValidator.isValidCvv(req.getCvv())) {
            throw new RuntimeException("CVV_INVALIDO");
        }

        YearMonth validade = YearMonth.of(req.getValidadeAno(), req.getValidadeMes());
        if (validade.isBefore(YearMonth.now())) {
            throw new RuntimeException("CARTAO_EXPIRADO");
        }

        String token = TokenizerUtils.encrypt(req.getNumeroCartao());
        String bandeira = CreditCardValidator.detectBrand(req.getNumeroCartao());

        entity.setCardToken(token);
        entity.setUltimosDigitos(req.getNumeroCartao().substring(req.getNumeroCartao().length() - 4));
        entity.setBandeira(bandeira);
        entity.setValidadeMes(req.getValidadeMes());
        entity.setValidadeAno(req.getValidadeAno());
        entity.setNomeTitular(req.getNomeTitular());
        entity.setCpfTitular(req.getCpfTitular());
        entity.setTipoCartao(TipoCartao.valueOf(req.getTipoCartao()));

        repository.save(entity);

        return PaymentMethodMapper.toDTO(entity);
    }
}

