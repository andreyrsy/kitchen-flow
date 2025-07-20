package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.model.KitchenModel;
import dev.andreyrsy.kitchen.flow.repository.KitchenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {
    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public KitchenModel adicionarAlimento(KitchenModel kitchenModel) {
        return kitchenRepository.saveAndFlush(kitchenModel);
    }

    public List<KitchenModel> listarAlimentos() {
        return kitchenRepository.findAll();
    }

    public void consumirAlimento(Long id, Integer quantidadeConsumida) {
        KitchenModel idUsuario = kitchenRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
        if(idUsuario.getQuantidade() >= quantidadeConsumida){
            idUsuario.setQuantidade(idUsuario.getQuantidade() - quantidadeConsumida);
        }else{
            System.out.println("Consumo maior do que a quantidade em estoque!");
        }
        kitchenRepository.saveAndFlush(idUsuario);
    }
}
