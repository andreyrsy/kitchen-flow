package dev.andreyrsy.kitchen.flow.exception.business;

public class DataInvalidaException extends RuntimeException {
    public DataInvalidaException(String mensagem) {
        super(mensagem);
    }

    public DataInvalidaException(String campo, String valor) {
        super("Data inválida para o campo '" + campo + "': " + valor);
    }
}
