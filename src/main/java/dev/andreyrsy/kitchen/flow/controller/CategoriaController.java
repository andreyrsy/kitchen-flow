package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.request.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.response.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.KitchenMapper;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.service.CategoriaService;
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
    private final KitchenMapper kitchenMapper;

    public CategoriaController(CategoriaService categoriaService, KitchenMapper kitchenMapper) {
        this.categoriaService = categoriaService;
        this.kitchenMapper = kitchenMapper;
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
        CategoriaResponseDto toResponseDto = kitchenMapper.toCategoriaResponseDto(categoria);
        return ResponseEntity.ok().body(toResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable(name = "id") Long id) {
        categoriaService.deletarPorId(id);
        return ResponseEntity.ok().body("Categoria de ID=" + id + " removido com sucesso");
    }
}
