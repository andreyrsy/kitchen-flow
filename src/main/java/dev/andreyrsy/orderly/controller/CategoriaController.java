package dev.andreyrsy.orderly.controller;

import dev.andreyrsy.orderly.dto.request.CategoriaRequestDto;
import dev.andreyrsy.orderly.dto.response.CategoriaResponseDto;
import dev.andreyrsy.orderly.mapper.ProjectMapper;
import dev.andreyrsy.orderly.model.Categoria;
import dev.andreyrsy.orderly.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final ProjectMapper projectMapper;

    public CategoriaController(CategoriaService categoriaService, ProjectMapper projectMapper) {
        this.categoriaService = categoriaService;
        this.projectMapper = projectMapper;
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
        CategoriaResponseDto toResponseDto = projectMapper.toCategoriaResponseDto(categoria);
        return ResponseEntity.ok().body(toResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@Valid @PathVariable(name = "id") Long id) {
        categoriaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
