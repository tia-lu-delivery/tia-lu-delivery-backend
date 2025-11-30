package br.com.fooddelivery.tialudeliveryback.security;

import org.springframework.stereotype.Component;

@Component
public class AuthValidator {

    public boolean isTokenAusente(String token) {
        return token == null || token.isBlank();
    }

    public boolean isTokenInvalido(String token) {
        return !token.equals("Bearer teste123");
    }
}
