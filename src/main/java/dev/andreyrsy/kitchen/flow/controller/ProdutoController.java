package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import dev.andreyrsy.kitchen.flow.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarAlimentos() {
        List<ProdutoResponseDto> listaProdutosx = produtoService.listarProdutos();
        return ResponseEntity.ok().body(listaProdutosx);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> adicionarProduto(@Valid @RequestBody ProdutoRequestDto dto) {
        ProdutoResponseDto responseDto = produtoService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
