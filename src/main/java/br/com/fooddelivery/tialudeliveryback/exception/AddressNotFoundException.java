package br.com.fooddelivery.tialudeliveryback.exception;

public class AddressNotFoundException extends RuntimeException {

    private final Long idEndereco;

    public AddressNotFoundException(Long idEndereco) {
        super(String.format("O endereço com ID '%d' não foi encontrado ou não pertence a este usuário.", idEndereco));
        this.idEndereco = idEndereco;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }
}