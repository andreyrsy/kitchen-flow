package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.service.LotesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<List<LotesResponseDto>> listarTodosLotes(){
        List<LotesResponseDto> listaLotes = lotesService.listarLotes();
        return ResponseEntity.ok().body(listaLotes);
    }

    @PostMapping
    public ResponseEntity<LotesResponseDto> salvarLote(@Valid @RequestBody LotesRequestDto dto) throws Exception {
        LotesResponseDto loteResponse = lotesService.criarLote(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(loteResponse);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarLotePorId(@PathVariable(name = "id") Long id){
        lotesService.deletarPorId(id);
    }

    @PutMapping("/{id}/consumir/{qtdConsumida}")
        public void usarProdutoController(@Valid @PathVariable(name = "id") Long id, @PathVariable(name = "qtdConsumida") Integer qtdConsumida) throws Exception {
        lotesService.usarProduto(id, qtdConsumida);
    }
}
