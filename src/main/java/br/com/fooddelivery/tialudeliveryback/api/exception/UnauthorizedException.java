package br.com.fooddelivery.tialudeliveryback.api.exception;

/**
 * Exceção usada quando não há autenticação/credenciais válidas.
 */
public class UnauthorizedException extends RuntimeException {
    private final String codigo;
    private final String detalhe;

    public UnauthorizedException(String codigo, String detalhe) {
        super(detalhe);
        this.codigo = codigo;
        this.detalhe = detalhe;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetalhe() {
        return detalhe;
    }
}
