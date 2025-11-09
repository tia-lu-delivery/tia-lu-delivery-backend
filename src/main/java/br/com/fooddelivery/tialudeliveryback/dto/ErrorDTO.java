package br.com.fooddelivery.tialudeliveryback.dto;

public class ErrorDTO {

    private String codigoErro;
    private String mensagem;

    public ErrorDTO() {
    }

    public ErrorDTO(String codigoErro, String mensagem) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
}
}