package br.com.fooddelivery.tialudeliveryback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoPedidoException extends RuntimeException {

    public ValidacaoPedidoException(String message) {
        super(message);
    }
}