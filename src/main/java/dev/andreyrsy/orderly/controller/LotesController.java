package dev.andreyrsy.orderly.controller;

import dev.andreyrsy.orderly.dto.request.ConsumoRequestDto;
import dev.andreyrsy.orderly.dto.response.ConsumoResponseDto;
import dev.andreyrsy.orderly.dto.request.LotesRequestDto;
import dev.andreyrsy.orderly.dto.response.LotesResponseDto;
import dev.andreyrsy.orderly.mapper.ProjectMapper;
import dev.andreyrsy.orderly.model.Lotes;
import dev.andreyrsy.orderly.service.LotesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotes")
@Tag(name = "Lotes", description = "Endpoints para gerenciamento de lotes e consumo de estoque")
public class LotesController {
    private final LotesService lotesService;
    private final ProjectMapper projectMapper;

    public LotesController(LotesService lotesService, ProjectMapper projectMapper) {
        this.lotesService = lotesService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public ResponseEntity<List<LotesResponseDto>> listarTodosLotes() {
        List<LotesResponseDto> listarLotesResponse = lotesService.listarTodosLotes();
        return ResponseEntity.ok().body(listarLotesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotesResponseDto> buscarLotePorId(@Valid @PathVariable(name = "id") Long id) {
        Lotes lotesSelecionado = lotesService.findById(id);
        LotesResponseDto toResponseDto = projectMapper.toLotesResponseDto(lotesSelecionado,
                lotesSelecionado.getProduto());

        return ResponseEntity.ok().body(toResponseDto);
    }

    @PostMapping
    public ResponseEntity<LotesResponseDto> criarLote(@Valid @RequestBody LotesRequestDto dto) throws Exception {
        LotesResponseDto toResponseDto = lotesService.salvarLote(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto);
    }

    @PostMapping("/consumir/{id}")
    public ResponseEntity<ConsumoResponseDto> usarProdutoDoLote(@Valid @PathVariable(name = "id") Long id,
            @RequestBody @Valid ConsumoRequestDto dto) throws Exception {
        ConsumoResponseDto toConsumoResponseDto = lotesService.utilizarProduto(id, dto.getQuantidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(toConsumoResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLotePorId(@Valid @PathVariable(name = "id") Long id) {
        lotesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
