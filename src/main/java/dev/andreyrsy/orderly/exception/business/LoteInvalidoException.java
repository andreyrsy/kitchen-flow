package dev.andreyrsy.orderly.exception.business;

public class LoteInvalidoException extends RuntimeException{
    public LoteInvalidoException(String nome){
        super("Lotes do produto" + nome + " inválido!");
    }
}
