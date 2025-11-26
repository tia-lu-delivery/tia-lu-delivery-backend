package br.com.fooddelivery.tialudeliveryback.exceptions;

/**
 * Exceção lançada quando um endereço não é encontrado
 */
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
