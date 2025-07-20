package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.model.KitchenModel;
import dev.andreyrsy.kitchen.flow.service.KitchenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KitchenController {
    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @PostMapping
    public ResponseEntity<KitchenModel> adicionarAlimento(@RequestBody KitchenModel kitchenModel) {
        KitchenModel kitchen = kitchenService.adicionarAlimento(kitchenModel);
        return new ResponseEntity<>(kitchen, HttpStatus.CREATED);
    }

    @GetMapping
    public List<KitchenModel> listarAlimentos(){
        return kitchenService.listarAlimentos();
    }

    @PutMapping("/produto/{id}/qtd/{qtd_consumida}")
    public ResponseEntity<KitchenModel> consumirAlimento(@PathVariable("id") Long id, @PathVariable("qtd_consumida") Integer qtd_consumida) {
        kitchenService.consumirAlimento(id, qtd_consumida);
        return ResponseEntity.ok().build();
    }
}
