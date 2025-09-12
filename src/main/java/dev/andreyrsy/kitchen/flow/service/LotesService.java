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

    // Lógica do Lotes service para adicionar produtos, listar etc...
    public Lotes criarLote(Lotes lotes){
        return lotesRepository.save(lotes);
    }

    public List<Lotes> listarLotes(){
        return lotesRepository.findAll();
    }

    public void deletarPorId(Long id){
        lotesRepository.deleteById(id);
    }
}
