package br.com.fooddelivery.tialudeliveryback.service.exception;

public class EmailJaCadastradoException extends RuntimeException {

    private final String email;

    public EmailJaCadastradoException(String email) {
        super("O e-mail '" + email + "' já está cadastrado.");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
