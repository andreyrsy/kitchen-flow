package dev.andreyrsy.orderly.exception.business;

public class CategoriaDuplicadaException extends RuntimeException{
    public CategoriaDuplicadaException(String name){
        super("Categoria com o nome [" + name + "] já existe!");
    }
}
