package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.repository.LotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotesService {
    private final LotesRepository lotesRepository;

    public LotesService(LotesRepository lotesRepository){
        this.lotesRepository = lotesRepository;
    }

    public Lotes criarLote(Lotes lotes) throws Exception {
        if(lotes.getData_validade().isBefore(lotes.getData_entrada())){
            throw new Exception("Insira uma data de validade correta!");
        }
        return lotesRepository.save(lotes);
    }

    public List<Lotes> listarLotes(){
        return lotesRepository.findAll();
    }

    public void deletarPorId(Long id){
        lotesRepository.deleteById(id);
    }

    public Lotes findById(Long id){
        return lotesRepository.findById(id).get();
    }

    public void usarProduto(Long id, Integer quantidadeConsumida) throws Exception {
        Lotes idProduto = lotesRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        if (idProduto.getQuantidade() >= quantidadeConsumida) {
            idProduto.setQuantidade(idProduto.getQuantidade() - quantidadeConsumida);
        } else {
            throw new Exception("Quantidade insuficiente no estoque.");
        }
        lotesRepository.saveAndFlush(idProduto);
    }
}
