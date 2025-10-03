package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ConsumoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ConsumoResponseDto;
import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.dto.LotesResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.LotesMapper;
import dev.andreyrsy.kitchen.flow.model.Lotes;
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
    private final LotesMapper mapper;

    public LotesController(LotesService lotesService, LotesMapper mapper) {
        this.lotesService = lotesService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<LotesResponseDto>> listarTodosLotes() {
        List<LotesResponseDto> listarLotesResponse = lotesService.listarTodosLotes();
        return ResponseEntity.ok().body(listarLotesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotesResponseDto> buscarLotePorId(@Valid @PathVariable(name = "id") Long id) {
        Lotes loteSelecionado = lotesService.findById(id);
        LotesResponseDto toResponseDto = mapper.toDto(loteSelecionado, loteSelecionado.getProduto());
        return ResponseEntity.ok().body(toResponseDto);
    }

    @PostMapping
    public ResponseEntity<LotesResponseDto> criarLote(@Valid @RequestBody LotesRequestDto dto) throws Exception {
        LotesResponseDto toResponseDto = lotesService.salvarLote(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLotePorId(@PathVariable(name = "id") Long id) {
        lotesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/consumos")
    public ResponseEntity<ConsumoResponseDto> usarProduto(@PathVariable(name = "id") Long id, @RequestBody @Valid ConsumoRequestDto dto) throws Exception {
        ConsumoResponseDto toConsumoResponseDto = lotesService.utilizarProduto(id, dto.getQuantidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(toConsumoResponseDto);
    }
}
