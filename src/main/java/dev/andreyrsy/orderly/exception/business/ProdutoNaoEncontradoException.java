package dev.andreyrsy.orderly.exception.business;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id) {
        super("Produto com o ID " + id + " não encontrado");
    }

    public ProdutoNaoEncontradoException(String nome) {
        super("Produto '" + nome + "' não encontrado");
    }
}
