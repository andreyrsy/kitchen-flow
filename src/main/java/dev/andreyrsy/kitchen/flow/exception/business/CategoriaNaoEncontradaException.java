package dev.andreyrsy.kitchen.flow.exception.business;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria com ID " + id + " não encontrada");
    }

    public CategoriaNaoEncontradaException(String nome) {
        super("Categoria '" + nome + "' não encontrada");
    }
}
