package dev.andreyrsy.kitchen.flow.exception.business;

public class QuantidadeInsuficienteException extends RuntimeException {
    public QuantidadeInsuficienteException(Long loteId, Integer quantidadeDisponivel, Integer quantidadeSolicitada) {
        super(String.format("Quantidade insuficiente no lote %d. Disponível: %d, Solicitado: %d",
                loteId, quantidadeDisponivel, quantidadeSolicitada));
    }

    public QuantidadeInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
