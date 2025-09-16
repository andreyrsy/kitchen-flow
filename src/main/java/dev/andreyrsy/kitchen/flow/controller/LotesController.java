package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.dto.LotesRequestDto;
import dev.andreyrsy.kitchen.flow.model.Lotes;
import dev.andreyrsy.kitchen.flow.model.Produto;
    import dev.andreyrsy.kitchen.flow.service.LotesService;
import dev.andreyrsy.kitchen.flow.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotes")
public class LotesController {
    private final LotesService lotesService;
    private final ProdutoService produtoService;
    public LotesController(LotesService lotesService, ProdutoService produtoService) {
        this.lotesService = lotesService;
        this.produtoService = produtoService;
    }

    // Entidades @PostMapping, @GetMapping e @DeleteMapping...
    @GetMapping
    public ResponseEntity<List<Lotes>> listarLotes(){
        List<Lotes> todosLotes = lotesService.listarLotes();
        return ResponseEntity.ok().body(todosLotes);
    }

    @PostMapping
    public ResponseEntity<Lotes> salvarLotes(@RequestBody LotesRequestDto dto) throws Exception {
        Produto produtoSelecionado = produtoService.findById(dto.getProdutoId());

        Lotes lote = new Lotes();
        lote.setQuantidade(dto.getQuantidade());
        lote.setData_validade(dto.getData_validade());
        lote.setData_entrada(dto.getData_entrada());
        lote.setProduto(produtoSelecionado);

        Lotes salvarLote = lotesService.criarLote(lote);

        return ResponseEntity.ok().body(salvarLote);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarLotePorId(@PathVariable(name = "id") Long id){
        lotesService.deletarPorId(id);
    }

    @PutMapping("/{id}/consumir/{qtdConsumida}")
        public void usarProdutoController(@PathVariable(name = "id") Long id, @PathVariable(name = "qtdConsumida") Integer qtdConsumida) throws Exception {
        lotesService.usarProduto(id, qtdConsumida);
    }
}
