package dev.andreyrsy.orderly.controller;

import dev.andreyrsy.orderly.dto.request.ProdutoRequestDto;
import dev.andreyrsy.orderly.dto.response.ProdutoResponseDto;
import dev.andreyrsy.orderly.mapper.ProjectMapper;
import dev.andreyrsy.orderly.model.Produto;
import dev.andreyrsy.orderly.service.ProdutoService;
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
    private final ProjectMapper projectMapper;

    public ProdutoController(ProdutoService produtoService, ProjectMapper projectMapper) {
        this.produtoService = produtoService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodosProdutos() {
        List<ProdutoResponseDto> listarProdutos = produtoService.listarProdutos();
        return ResponseEntity.ok().body(listarProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@Valid @PathVariable(name = "id") Long id) {
        Produto produtoSelecionado = produtoService.findById(id);
        ProdutoResponseDto toResponseDto = projectMapper.toProdutoResponseDto(produtoSelecionado);
        return ResponseEntity.ok().body(toResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> adicionarProduto(@Valid @RequestBody ProdutoRequestDto dto) {
        ProdutoResponseDto toResponseDto = produtoService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable("id") Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().body("Produto de ID=" + id + " removido com sucesso");
    }
}
