package br.com.fooddelivery.tialudeliveryback.exception;

// Necessária para o Status 404 Not Found (Cenário 2)
public class CardapioNotFoundException extends RuntimeException {
    
    public CardapioNotFoundException(String idCardapio) {
        super(String.format("O cardápio com ID '%s' não foi encontrado ou não pertence a este estabelecimento.", idCardapio));
    }
}