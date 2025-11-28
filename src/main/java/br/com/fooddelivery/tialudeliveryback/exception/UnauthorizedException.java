package br.com.fooddelivery.tialudeliveryback.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RuntimeException {

    private final HttpStatus status = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
