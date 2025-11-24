package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.ProdutoMapper;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import dev.andreyrsy.kitchen.flow.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
@Tag(name = "Produto", description = "Endpoints para gerenciamento dos produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository, ProdutoMapper mapper) {
        this.produtoService = produtoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodosProdutos() {
        List<ProdutoResponseDto> listarProdutos = produtoService.listarProdutos();
        return ResponseEntity.ok().body(listarProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@PathVariable(name = "id") Long id) {
        Produto produtoSelecionado = produtoService.findById(id);
        ProdutoResponseDto toResponseDto = mapper.toDto(produtoSelecionado);
        return ResponseEntity.ok().body(toResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> adicionarProduto(@RequestBody @Valid ProdutoRequestDto dto) {
        ProdutoResponseDto toResponseDto = produtoService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().body("Produto de ID=" + id + " removido com sucesso");
    }
}
