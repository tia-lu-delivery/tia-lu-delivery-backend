package br.com.fooddelivery.tialudeliveryback.user.dto;

public record UserRegistrationResponseDto(
	String idUsuario,
	String mensagem,
	String tokenAcesso,
	long expiracaoToken
) {
}
