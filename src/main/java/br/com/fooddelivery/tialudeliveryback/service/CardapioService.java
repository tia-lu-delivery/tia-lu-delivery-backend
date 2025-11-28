package br.com.fooddelivery.tialudeliveryback.service;

import org.springframework.stereotype.Service;

@Service
public class CardapioService {
    public void excluirCardapio(Long id, Long merchantId) {
        System.out.println("MOCK: Deletando cardápio ID: " + id + " para o Merchant: " + merchantId);
    }
}