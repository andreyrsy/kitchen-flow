package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.ProdutoDto;
import dev.andreyrsy.kitchen.flow.model.ProdutoModel;
import dev.andreyrsy.kitchen.flow.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoModel> adicionarAlimento(@RequestBody ProdutoModel produtoModel) {
        ProdutoModel kitchen = produtoService.adicionarAlimento(produtoModel);
        return new ResponseEntity<>(kitchen, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProdutoDto> listarAlimentos(){
        return produtoService.listarAlimentos();
    }

    @PutMapping("/produto/{id}/qtd/{qtd_consumida}")
    public ResponseEntity<ProdutoModel> consumirAlimento(@PathVariable("id") Long id, @PathVariable("qtd_consumida") Integer qtd_consumida) throws Exception {
        produtoService.consumirAlimento(id, qtd_consumida);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public void removerAlimento(@PathVariable("id") Long id){
        produtoService.deletarAlimento(id);
    }
}
