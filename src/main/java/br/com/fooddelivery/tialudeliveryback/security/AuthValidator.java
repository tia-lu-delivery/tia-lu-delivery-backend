package br.com.fooddelivery.tialudeliveryback.security;

import org.springframework.stereotype.Component;

@Component
public class AuthValidator {

    private static final String TOKEN_VALIDO = "Bearer teste123";

    public boolean isTokenAusente(String tokenHeader) {
        return tokenHeader == null || tokenHeader.isBlank();
    }

    public boolean isTokenInvalido(String tokenHeader) {
        if (tokenHeader == null) return true;
        return !TOKEN_VALIDO.equals(tokenHeader);
    }
}