package br.com.fooddelivery.tialudeliveryback.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Endereço com ID '" + id + "' não encontrado ou não pertence ao usuário.");
    }
}
