package dev.andreyrsy.kitchen.flow.exception.business;

public class ProdutoDuplicadoException extends RuntimeException {
    public ProdutoDuplicadoException(String nome) {
        super("Já existe um produto com o nome '" + nome + "'");
    }
}
