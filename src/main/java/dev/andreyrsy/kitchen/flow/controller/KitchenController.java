package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.KitchenResponseDTO;
import dev.andreyrsy.kitchen.flow.model.KitchenModel;
import dev.andreyrsy.kitchen.flow.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class KitchenController {
    private final KitchenService kitchenService;

    @PostMapping
    public ResponseEntity<KitchenModel> adicionarAlimento(@RequestBody KitchenModel kitchenModel) {
        KitchenModel kitchen = kitchenService.adicionarAlimento(kitchenModel);
        return new ResponseEntity<>(kitchen, HttpStatus.CREATED);
    }

    @GetMapping
    public List<KitchenResponseDTO> listarAlimentos(){
        return kitchenService.listarAlimentos();
    }

    @PutMapping("/produto/{id}/qtd/{qtd_consumida}")
    public ResponseEntity<KitchenModel> consumirAlimento(@PathVariable("id") Long id, @PathVariable("qtd_consumida") Integer qtd_consumida) throws Exception {
        kitchenService.consumirAlimento(id, qtd_consumida);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public void removerAlimento(@PathVariable("id") Long id){
        kitchenService.deletarAlimento(id);
    }
}
