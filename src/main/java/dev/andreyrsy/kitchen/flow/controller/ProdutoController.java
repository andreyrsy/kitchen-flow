package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.service.CategoriaService;
import dev.andreyrsy.kitchen.flow.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarAlimentos() {
        List<ProdutoResponseDto> listaProdutosx = produtoService.listarProdutos();
        return ResponseEntity.ok().body(listaProdutosx);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@Valid @RequestBody ProdutoRequestDto dto) {
        Categoria categoriaSelecionada = categoriaService.findById(dto.getCategoriaId());

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setUnidadeMedida(dto.getUnidadeMedida());
        produto.setCategoria(categoriaSelecionada);

        Produto produtoAdicionado = produtoService.criarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoAdicionado);
    }

    @DeleteMapping("/deletar/{id}")
    public void removerAlimento(@PathVariable("id") Long id) {
        produtoService.deletarProduto(id);
        ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
