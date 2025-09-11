package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.repository.LotesRepository;
import org.springframework.stereotype.Service;

@Service
public class LotesService {
    private final LotesRepository lotesRepository;

    public LotesService(LotesRepository lotesRepository){
        this.lotesRepository = lotesRepository;
    }

    // Lógica do Lotes service para adicionar produtos, listar etc...
}
