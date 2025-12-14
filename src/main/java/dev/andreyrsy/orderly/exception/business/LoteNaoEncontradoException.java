package dev.andreyrsy.orderly.exception.business;

public class LoteNaoEncontradoException extends RuntimeException {
    public LoteNaoEncontradoException(Long id) {
        super("Lotes com ID " + id + " não encontrado");
    }
}
