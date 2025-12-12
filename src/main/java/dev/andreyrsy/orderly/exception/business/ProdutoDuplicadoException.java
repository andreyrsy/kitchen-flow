package dev.andreyrsy.orderly.exception.business;

public class ProdutoDuplicadoException extends RuntimeException {
    public ProdutoDuplicadoException(String nome) {
        super("Já existe um produto com o nome '" + nome + "'");
    }
}
