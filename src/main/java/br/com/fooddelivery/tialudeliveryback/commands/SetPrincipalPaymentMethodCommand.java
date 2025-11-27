package br.com.foodelivery.tialudeliveryback.commands;

public record SetPrincipalPaymentMethodCommand(Long userId, String paymentMethodId) {}