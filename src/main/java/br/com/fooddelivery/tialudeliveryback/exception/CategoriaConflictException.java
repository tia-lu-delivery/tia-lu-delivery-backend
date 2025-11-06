package br.com.fooddelivery.tialudeliveryback.exception;

// Necessária para o Status 409 Conflict (Cenário 3)
public class CategoriaConflictException extends RuntimeException {
    
    private final String campo;
    
    public CategoriaConflictException(String nomeCategoria, String campo) {
        super(String.format("O nome de categoria '%s' já existe neste cardápio.", nomeCategoria));
        this.campo = campo;
    }
    
    public String getCampo() {
        return campo;
    }
}