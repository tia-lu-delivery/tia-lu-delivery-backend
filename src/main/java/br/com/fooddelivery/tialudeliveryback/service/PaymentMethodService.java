package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.UpdatePaymentMethodRequest;
import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodResponseDTO;
import br.com.fooddelivery.tialudeliveryback.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.enum_.TipoCartao;
import br.com.fooddelivery.tialudeliveryback.mapper.PaymentMethodMapper;
import br.com.fooddelivery.tialudeliveryback.repository.PaymentMethodRepository;
import br.com.fooddelivery.tialudeliveryback.util.CreditCardValidator;
import br.com.fooddelivery.tialudeliveryback.exception.FieldValidationError;
import br.com.fooddelivery.tialudeliveryback.exception.ValidationException;
import br.com.fooddelivery.tialudeliveryback.exception.ResourceNotFoundException;
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

        Long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ValidationException("Dados do cartão inválidos ou insuficientes.",
                    java.util.List.of(new FieldValidationError("id", "ID inválido.")));
        }

        PaymentMethod entity = repository.findByIdAndUserId(idLong, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("O meio de pagamento com ID '%s' não foi encontrado ou não pertence a este usuário.", id)));

        java.util.List<FieldValidationError> erros = new java.util.ArrayList<>();

        // VALIDAR NUMERO DO CARTAO (Luhn)
        if (!CreditCardValidator.isValid(req.getNumeroCartao())) {
            erros.add(new FieldValidationError("numeroCartao", "Número do cartão inválido."));
        }

        // VALIDAR CVV (não será persistido)
        if (!CreditCardValidator.isValidCvv(req.getCvv())) {
            erros.add(new FieldValidationError("cvv", "O CVV fornecido é inválido."));
        }

        YearMonth validade = YearMonth.of(req.getValidadeAno(), req.getValidadeMes());
        if (validade.isBefore(YearMonth.now())) {
            erros.add(new FieldValidationError("validadeAno",
                    String.format("A data de validade (%02d/%d) está expirada.", req.getValidadeMes(), req.getValidadeAno())));
        }

        TipoCartao tipo = null;
        try {
            tipo = TipoCartao.valueOf(req.getTipoCartao());
        } catch (IllegalArgumentException | NullPointerException ex) {
            erros.add(new FieldValidationError("tipoCartao", "Tipo de cartão inválido."));
        }

        if (!erros.isEmpty()) {
            throw new ValidationException("Dados do cartão inválidos ou insuficientes.", erros);
        }
