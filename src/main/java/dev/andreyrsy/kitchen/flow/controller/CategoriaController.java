package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.CategoriaMapper;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final CategoriaMapper mapper;

    public CategoriaController(CategoriaService categoriaService, CategoriaMapper mapper) {
        this.categoriaService = categoriaService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@Valid @RequestBody CategoriaRequestDto categoria) {
        CategoriaResponseDto toResponseDto = categoriaService.criarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarTodasCategorias() {
        List<CategoriaResponseDto> listaDto = categoriaService.findAll();
        return ResponseEntity.ok().body(listaDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(@Valid @PathVariable(name = "id") Long id) {
        Categoria categoria = categoriaService.findById(id);
        CategoriaResponseDto toResponseDto = mapper.toResponseDto(categoria);
        return ResponseEntity.ok().body(toResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable(name = "id") Long id) {
        categoriaService.deletarPorId(id);
        return ResponseEntity.ok().body("Categoria de ID=" + id + " removido com sucesso");
    }
}
