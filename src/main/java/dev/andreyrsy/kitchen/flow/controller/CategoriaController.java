package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@Valid @RequestBody CategoriaRequestDto categoria){
        CategoriaResponseDto categoriaResponseDto = categoriaService.criarCategoria(categoria);
        return ResponseEntity.ok().body(categoriaResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias(){
        List<CategoriaResponseDto> lisatDto = categoriaService.findAll();
        return ResponseEntity.ok().body(lisatDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable(name = "id") Long id){
        categoriaService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
