package br.com.fooddelivery.tialudeliveryback.security;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorEnvelope;
import br.com.fooddelivery.tialudeliveryback.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class XUserIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/api/v1/users/address")) {
            String userId = request.getHeader("X-User-Id");
            if (userId == null || userId.isBlank()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                ErrorEnvelope envelope = ErrorEnvelope.builder()
                        .erro(ErrorEnvelope.Error.builder()
                                .codigo(ErrorCode.NAO_AUTORIZADO.name())
                                .detalhe("Token de autenticação ausente ou inválido. Não foi possível carregar o endereço.")
                                .build())
                        .build();
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(envelope));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
