package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.UpdatePaymentMethodRequest;
import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponse;
import br.com.fooddelivery.tialudeliveryback.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.exception.NotFoundException;
import br.com.fooddelivery.tialudeliveryback.repository.PaymentMethodRepository;
import br.com.fooddelivery.tialudeliveryback.util.SecurityUtils;
import br.com.fooddelivery.tialudeliveryback.util.CreditCardValidator;
import br.com.fooddelivery.tialudeliveryback.util.TokenizerUtils;
import br.com.fooddelivery.tialudeliveryback.mapper.PaymentMethodMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository repository;
    private final PaymentMethodMapper mapper;

    public PaymentMethodService(PaymentMethodRepository repository, PaymentMethodMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PaymentMethodResponse updatePaymentMethod(Long id, UpdatePaymentMethodRequest req) {

        Long userId = SecurityUtils.getAuthenticatedUserId();

        PaymentMethod pm = repository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() ->
                        new NotFoundException("O meio de pagamento não foi encontrado ou não pertence ao usuário."));

        // --- validações (#142)
        if (!CreditCardValidator.luhnCheck(req.getNumeroCartao()))
            throw new IllegalArgumentException("Número de cartão inválido");

        if (!CreditCardValidator.cvvValid(req.getCvv()))
            throw new IllegalArgumentException("CVV inválido");

        if (!CreditCardValidator.expiryValid(req.getValidadeMes(), req.getValidadeAno()))
            throw new IllegalArgumentException("Data de validade expirada");

        if (!CreditCardValidator.tipoCartaoValido(req.getTipoCartao()))
            throw new IllegalArgumentException("tipoCartao inválido");

        String tokenCard = TokenizerUtils.tokenize(req.getNumeroCartao());

        pm.setCardToken(tokenCard);
        pm.setLast4(CreditCardValidator.last4(req.getNumeroCartao()));
        pm.setBrand(CreditCardValidator.detectBrand(req.getNumeroCartao()));
        pm.setHolderName(req.getNomeTitular());
        pm.setCpfHolder(req.getCpfTitular());
        pm.setExpiryMonth(req.getValidadeMes());
        pm.setExpiryYear(req.getValidadeAno());
        pm.setCardType(req.getTipoCartao().toUpperCase());

        repository.save(pm);

        return mapper.toResponse(pm);
    }
}
