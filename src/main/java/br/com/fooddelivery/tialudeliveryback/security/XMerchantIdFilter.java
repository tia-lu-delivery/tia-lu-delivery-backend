package br.com.fooddelivery.tialudeliveryback.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorEnvelope;
import br.com.fooddelivery.tialudeliveryback.exception.ErrorCode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro simples provisório para validar o header X-Merchant-Id quando a rota
 * for a de habilitar produto. Para produção, usar Spring Security e claims do token.
 */
@Component
public class XMerchantIdFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(XMerchantIdFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        // Padrão: /api/v1/merchant/{merchantId}/products/{productId}/enable
        if (path != null && path.startsWith("/api/v1/merchant/") && path.contains("/products/") && path.endsWith("/enable")) {
            String[] parts = path.split("/");
            String merchantId = null;
            for (int i = 0; i < parts.length; i++) {
                if ("merchant".equals(parts[i]) && i + 1 < parts.length) {
                    merchantId = parts[i + 1];
                    break;
                }
            }

            String header = request.getHeader("X-Merchant-Id");
            if (merchantId == null) {
                log.debug("Não foi possível extrair merchantId da rota: {}", path);
                // Retornar diretamente um envelope JSON com 401 para que o comportamento
                // seja consistente mesmo quando o filtro é executado antes do ControllerAdvice.
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                ErrorEnvelope envelope = ErrorEnvelope.of(ErrorCode.NAO_AUTORIZADO.toString(), "ID do estabelecimento não encontrado na rota");
                new ObjectMapper().writeValue(response.getWriter(), envelope);
                return;
            }

            if (header == null || !header.equals(merchantId)) {
                log.info("Acesso não autorizado: header X-Merchant-Id={} pathMerchant={}", header, merchantId);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                ErrorEnvelope envelope = ErrorEnvelope.of(ErrorCode.NAO_AUTORIZADO.toString(), "Header X-Merchant-Id ausente ou diferente do id do estabelecimento");
                new ObjectMapper().writeValue(response.getWriter(), envelope);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
