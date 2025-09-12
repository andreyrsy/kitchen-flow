package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.service.LotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotes")
public class LotesController {
    private final LotesService lotesService;

    public LotesController(LotesService lotesService) {
        this.lotesService = lotesService;
    }

    // Entidades @PostMapping, @GetMapping e @DeleteMapping...
    @GetMapping
    public ResponseEntity<List<Lotes>> listarLotes(){
        List<Lotes> todosLotes = lotesService.listarLotes();
        return ResponseEntity.ok().body(todosLotes);
    }

    @PostMapping
    public ResponseEntity<Lotes> salvarLotes(@RequestBody Lotes lotes){
        Lotes salvarLote = lotesService.criarLote(lotes);
        return ResponseEntity.ok().body(salvarLote);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarLotePorId(@PathVariable(name = "id") Long id){
        lotesService.deletarPorId(id);
    }
}
