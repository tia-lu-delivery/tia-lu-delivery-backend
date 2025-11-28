package br.com.fooddelivery.tialudeliveryback.util;

import br.com.fooddelivery.tialudeliveryback.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new UnauthorizedException("Usuário não autenticado");
        }

        Object principal = auth.getPrincipal();
        if (principal == null) {
            throw new UnauthorizedException("Usuário não autenticado");
        }
        
        try {
            var method = principal.getClass().getMethod("getId");
            Object idObj = method.invoke(principal);
            if (idObj instanceof Number) {
                return ((Number) idObj).longValue();
            }
        } catch (NoSuchMethodException ignored) {
        } catch (Exception ignored) {
        }
        
        if (principal instanceof Number) {
            return ((Number) principal).longValue();
        }

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            try {
                return Long.parseLong(username);
            } catch (NumberFormatException ignored) {}
        }

        if (principal instanceof String) {
            try {
                return Long.parseLong((String) principal);
            } catch (NumberFormatException ignored) {}
        }

        throw new UnauthorizedException("Não foi possível extrair userId do contexto de segurança");
    }
}
