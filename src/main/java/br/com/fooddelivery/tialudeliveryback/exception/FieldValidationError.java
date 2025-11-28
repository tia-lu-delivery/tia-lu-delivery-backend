package br.com.fooddelivery.tialudeliveryback.exception;

public class FieldValidationError {
    private String campo;
    private String mensagem;

    public FieldValidationError() {}

    public FieldValidationError(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
