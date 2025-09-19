package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.repository.LotesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LotesService {
    private final LotesRepository lotesRepository;

    public LotesService(LotesRepository lotesRepository) {
        this.lotesRepository = lotesRepository;
    }

    public Lotes criarLote(Lotes lotes) throws Exception {
        log.info("Criando lote produto={} quantidade={} dataEntrada={} dataValidade={}",
                lotes.getProduto().getNome(), lotes.getQuantidade(),
                lotes.getData_entrada(), lotes.getData_validade());

        if (lotes.getData_validade().isBefore(lotes.getData_entrada())) {
            log.error("Data de validade inválida dataValidade={} dataEntrada={}",
                    lotes.getData_validade(), lotes.getData_entrada());
            throw new Exception("Insira uma data de validade correta!");
        }

        Lotes loteSalvo = lotesRepository.save(lotes);

        log.info("Lote criado com sucesso id={} produto={}",
                loteSalvo.getId(), loteSalvo.getProduto().getNome());
        return loteSalvo;
    }

    public List<Lotes> listarLotes() {
        log.info("Listando todos os lotes");
        List<Lotes> lotesList = lotesRepository.findAll();
        log.info("Encontrados {} lotes", lotesList.size());
        return lotesList;
    }

    public Lotes findById(Long id) {
        log.debug("Buscando lote por id={}", id);
        Lotes lotes = lotesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lote não encontrado!"));
        log.debug("Lote encontrado id={} produto={} quantidade={}",
                lotes.getId(), lotes.getProduto().getNome(), lotes.getQuantidade());
        return lotes;
    }

    public void usarProduto(Long id, Integer quantidadeConsumida) throws Exception {
        log.info("Iniciando consumo do lote id={} quantidadeConsumida={}", id, quantidadeConsumida);
        Lotes idProduto = lotesRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        if (idProduto.getQuantidade() >= quantidadeConsumida) {
            idProduto.setQuantidade(idProduto.getQuantidade() - quantidadeConsumida);
            log.info("Consumo realizado com sucesso loteId={} quantidadeRestante={}", id, idProduto.getQuantidade());
        } else {
            log.error("Quantidade insuficiente no estoque loteId={} quantidadeDisponivel={} quantidadeSolicitada={}", id, idProduto.getQuantidade(), quantidadeConsumida);
            throw new Exception("Quantidade insuficiente no estoque.");
        }
        lotesRepository.saveAndFlush(idProduto);
    }
    
    public void deletarPorId(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        lotesRepository.deleteById(id);
        log.info("Lote deletado com sucesso id={}", id);
    }
}
