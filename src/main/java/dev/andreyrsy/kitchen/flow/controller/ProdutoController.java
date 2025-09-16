package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.service.CategoriaService;
import dev.andreyrsy.kitchen.flow.service.ProdutoService;
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
    public List<ProdutoResponseDto> listarAlimentos(){
        return produtoService.listarProdutos();
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody ProdutoRequestDto dto){
        Categoria categoriaSelecionada = categoriaService.findById(dto.getCategoriaId());

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setUnidadeMedida(dto.getUnidadeMedida());
        produto.setCategoria(categoriaSelecionada);

        Produto produtoAdicionado = produtoService.adicionarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoAdicionado);
    }

//    @PostMapping
//    public ResponseEntity<Produto> adicionarAlimento(@RequestBody Produto produto) {
//        Produto kitchen = produtoService.adicionarProduto(produto);
//        return new ResponseEntity<>(kitchen, HttpStatus.CREATED);
//    }
//
//
//    @PutMapping("/produto/{id}/qtd/{qtd_consumida}")
//    public ResponseEntity<Produto> consumirAlimento(@PathVariable("id") Long id, @PathVariable("qtd_consumida") Integer qtd_consumida) throws Exception {
//        produtoService.consumirAlimento(id, qtd_consumida);
//        return ResponseEntity.ok().build();
//    }
//
    @DeleteMapping("/deletar/{id}")
    public void removerAlimento(@PathVariable("id") Long id){
        produtoService.deletarProduto(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT).body(id.toString());
    }
}
