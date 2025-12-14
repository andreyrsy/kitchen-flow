package dev.andreyrsy.orderly.exception.business;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException(String nome) {
        super("Produto " + nome + " inválido!");
    }
}
