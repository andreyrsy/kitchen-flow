package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ConsumoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ConsumoResponseDto;
import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.repository.LotesRepository;
import dev.andreyrsy.kitchen.flow.service.LotesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotes")
public class LotesController {
    private final LotesService lotesService;
    private final LotesRepository lotesRepository;

    public LotesController(LotesService lotesService, LotesRepository lotesRepository) {
        this.lotesService = lotesService;
        this.lotesRepository = lotesRepository;
    }

    @GetMapping
    public ResponseEntity<List<LotesResponseDto>> listarTodosLotes() {
        List<LotesResponseDto> listarLotesResponse = lotesService.listarTodosLotes();
        return ResponseEntity.ok().body(listarLotesResponse);
    }

    @PostMapping
    public ResponseEntity<LotesResponseDto> criarLote(@Valid @RequestBody LotesRequestDto dto) throws Exception {
        LotesResponseDto criarLoteResponse = lotesService.salvarLote(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarLoteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLotePorId(@PathVariable(name = "id") Long id) {
        lotesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        lotesService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/consumos")
    public ResponseEntity<ConsumoResponseDto> usarProduto(@PathVariable(name = "id") Long id, @RequestBody ConsumoRequestDto dto) throws Exception {
//        LotesResponseDto atualizado = lotesService.utilizarProduto(id, dto.getQuantidade());
        ConsumoResponseDto atualizado = lotesService.utilizarProduto(id, dto.getQuantidade());

        return ResponseEntity.status(HttpStatus.CREATED).body(atualizado);
    }
}
