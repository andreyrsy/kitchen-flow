package dev.andreyrsy.kitchen.flow.exception.business;

public class LoteNaoEncontradoException extends RuntimeException {
    public LoteNaoEncontradoException(Long id) {
        super("Lote com ID " + id + " não encontrado");
    }
}
