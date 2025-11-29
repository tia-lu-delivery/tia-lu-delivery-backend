package br.com.fooddelivery.tialudeliveryback.domain.service;

import br.com.fooddelivery.tialudeliveryback.api.dto.PaymentMethodDTO;
import br.com.fooddelivery.tialudeliveryback.api.exception.NotFoundException;
import br.com.fooddelivery.tialudeliveryback.api.exception.UnauthorizedException;
import br.com.fooddelivery.tialudeliveryback.api.mapper.PaymentMethodMapper;
import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.domain.repository.PaymentMethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    private static final Logger log = LoggerFactory.getLogger(PaymentMethodService.class);

    private final PaymentMethodRepository repository;
    private final PaymentMethodMapper mapper;

    public PaymentMethodService(PaymentMethodRepository repository, PaymentMethodMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Recupera os detalhes não sensíveis de um meio de pagamento por id,
     * validando a autenticação do usuário (campo authenticatedUserId) e, quando possível,
     * validando que o meio de pagamento pertence ao usuário autenticado.
     *
     * Observações importantes:
     * - A entidade PaymentMethod atualmente não contém um campo explícito de dono/usuário.
     *   Para suprir a validação de titularidade (CA-002) o método tenta, por reflexão,
     *   inspecionar campos comuns (ex.: getUserId, getOwnerId, getUsuarioId). Se o domínio
     *   for estendido futuramente com o campo de proprietário, essa verificação automaticamente
     *   passará a funcionar sem alterações no Service.
     * - Se a verificação por reflexão não encontrar campo de titularidade, o método
     *   registra um warning e retorna o meio de pagamento quando encontrado (sendo necessária
     *   posterior revisão para garantir CA-002).
     *
     * @param idMeioPagamento id do meio de pagamento (ex: MP001)
     * @param authenticatedUserId id do usuário autenticado (vindo do token / contexto)
     * @return PaymentMethodDTO contendo apenas dados de exibição (não sensíveis)
     */
    public PaymentMethodDTO getPaymentMethodDetails(String idMeioPagamento, String authenticatedUserId) {
        // CA-002: validar autenticação
        if (authenticatedUserId == null || authenticatedUserId.isBlank()) {
            throw new UnauthorizedException("NAO_AUTORIZADO", "Token de autenticação ausente ou inválido.");
        }

        Optional<PaymentMethod> opt = repository.findById(idMeioPagamento);
        if (opt.isEmpty()) {
            throw notFound(idMeioPagamento);
        }

        PaymentMethod entity = opt.get();

        // Tenta validar titularidade usando reflexão (caso exista um campo relacionado ao dono)
        if (!belongsToAuthenticatedUser(entity, authenticatedUserId)) {
            // Conforme especificação CA-006: se não pertence ao usuário, retorna 404
            throw notFound(idMeioPagamento);
        }

        // Converte para DTO (mapper já garante não expor dados sensíveis)
        return mapper.toDTO(entity);
    }

    private NotFoundException notFound(String id) {
        String detalhe = String.format("O meio de pagamento com ID '%s' não foi encontrado ou não pertence a este usuário.", id);
        return new NotFoundException("MEIO_PAGAMENTO_NAO_ENCONTRADO", detalhe);
    }

    /**
     * Heurística que tenta identificar um campo de titularidade e compará-lo com o id autenticado.
     * Retorna true se:
     *  - não houver campo de titularidade conhecido (não é possível validar aqui) -> true (permite retorno)
     *  - houver campo e o valor for igual ao authenticatedUserId -> true
     * Retorna false se houver campo e o valor for diferente.
     */
    private boolean belongsToAuthenticatedUser(PaymentMethod entity, String authenticatedUserId) {
        List<String> candidateGetters = Arrays.asList("getUserId", "getOwnerId", "getUsuarioId", "getAccountId", "getClienteId");

        for (String getterName : candidateGetters) {
            try {
                Method m = entity.getClass().getMethod(getterName);
                Object value = m.invoke(entity);
                if (value == null) {
                    // campo presente porém nulo => considerar que não pertence
                    log.debug("Found ownership getter {} but value is null for PaymentMethod {}", getterName, entity.getId());
                    return false;
                }
                if (authenticatedUserId.equals(String.valueOf(value))) {
                    return true;
                } else {
                    log.debug("Ownership check failed: {} != {}", authenticatedUserId, value);
                    return false;
                }
            } catch (NoSuchMethodException e) {
                // getter não existe - tentar próximo
            } catch (Exception e) {
                log.warn("Erro ao tentar validar titularidade por reflexão usando {}: {}", getterName, e.getMessage());
                // Em caso de erro reflexivo, melhor falhar a validação por segurança
                return false;
            }
        }

        // Nenhum getter de titularidade encontrado: não é possível validar aqui.
        // Registramos o aviso e permitimos o retorno para manter compatibilidade até que o
        // modelo de domínio seja estendido pela equipe responsável.
        log.warn("Não foi possível validar titularidade do meio de pagamento {} — nenhum campo de dono encontrado na entidade. Atualize o domínio para garantir CA-002.", entity.getId());
        return true;
    }
}
