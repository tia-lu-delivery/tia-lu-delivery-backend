package br.com.fooddelivery.tialudeliveryback.api.exception;

/**
 * Exceção lançada quando um recurso não é encontrado.
 * Contém código e detalhe para que handlers globais possam serializar conforme a especificação.
 */
public class NotFoundException extends RuntimeException {
    private final String codigo;
    private final String detalhe;

    public NotFoundException(String codigo, String detalhe) {
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
