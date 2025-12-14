package dev.andreyrsy.orderly.exception.business;

public class CategoriaPossuiProdutosException extends RuntimeException {
    public CategoriaPossuiProdutosException(Long id) {
        super("Categoria ID=" + id + " possui produtos associados e não pode ser deletada");
    }
}
