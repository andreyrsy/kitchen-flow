package dev.andreyrsy.kitchen.flow.exception.business;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(Long id) {
        super("Produto com o ID "+ id + " não encontrado");
    }

    public ProdutoNaoEncontradoException(String nome) {
        super("Produto '" +nome + "' não encontrado");
    }
}
