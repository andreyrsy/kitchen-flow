package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.request.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.response.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.KitchenMapper;
import dev.andreyrsy.kitchen.flow.model.Produto;
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
    private final KitchenMapper kitchenMapper;

    public ProdutoController(ProdutoService produtoService, KitchenMapper kitchenMapper) {
        this.produtoService = produtoService;
        this.kitchenMapper = kitchenMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodosProdutos() {
        List<ProdutoResponseDto> listarProdutos = produtoService.listarProdutos();
        return ResponseEntity.ok().body(listarProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@Valid @PathVariable(name = "id") Long id) {
        Produto produtoSelecionado = produtoService.findById(id);
        ProdutoResponseDto toResponseDto = kitchenMapper.toProdutoResponseDto(produtoSelecionado);
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
