package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.model.KitchenModel;
import dev.andreyrsy.kitchen.flow.repository.KitchenRepository;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public void adicionarAlimento(KitchenModel kitchenModel) {
        kitchenRepository.save(kitchenModel);
    }

    
}
