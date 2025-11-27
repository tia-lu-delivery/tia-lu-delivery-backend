package br.com.fooddelivery.tialudeliveryback.service.dto;

public class UserResponseDTO {

    private String idUsuario;
    private String mensagem;
    private String tokenAcesso;
    private long expiracaoToken; // em segundos

    public UserResponseDTO() {
    }

    public UserResponseDTO(String idUsuario, String mensagem, String tokenAcesso, long expiracaoToken) {
        this.idUsuario = idUsuario;
        this.mensagem = mensagem;
        this.tokenAcesso = tokenAcesso;
        this.expiracaoToken = expiracaoToken;
    }

    // Getters e Setters
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getTokenAcesso() { return tokenAcesso; }
    public void setTokenAcesso(String tokenAcesso) { this.tokenAcesso = tokenAcesso; }

    public long getExpiracaoToken() { return expiracaoToken; }
    public void setExpiracaoToken(long expiracaoToken) { this.expiracaoToken = expiracaoToken; }
}
