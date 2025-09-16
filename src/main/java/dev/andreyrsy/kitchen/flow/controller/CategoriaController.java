package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.service.CategoriaService;
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
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria){
        Categoria service = categoriaService.criarCategoria(categoria);
        return ResponseEntity.ok().body(service);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias(){
        List<CategoriaResponseDto> df = categoriaService.findAll();
        return ResponseEntity.ok().body(df);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable(name = "id") Long id){
        categoriaService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
