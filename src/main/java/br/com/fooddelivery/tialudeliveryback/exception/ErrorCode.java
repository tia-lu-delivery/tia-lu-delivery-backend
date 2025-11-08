package br.com.fooddelivery.tialudeliveryback.exception;

/**
 * Códigos de erro usados pela API (envelope de erro).
 */
public enum ErrorCode {
    PRODUTO_NAO_ENCONTRADO,
    NAO_AUTORIZADO,
    RECURSO_NAO_ENCONTRADO,
    ERRO;

    @Override
    public String toString() {
        return name();
    }
}
