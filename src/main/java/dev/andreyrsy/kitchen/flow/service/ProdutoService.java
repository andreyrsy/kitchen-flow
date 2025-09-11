package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }


    public List<Produto> listarProdutos() {
        List<Produto> produtosBanco = produtoRepository.findAll();
        return produtosBanco;
    }

    public StatusValidade calcularStatus(LocalDate dataValidade) {
        LocalDate dataAtual = LocalDate.now();
        long diasRestantes = ChronoUnit.DAYS.between(dataAtual, dataValidade);

        if (diasRestantes < 0) {
            return StatusValidade.VENCIDO;
        } else if (diasRestantes <= 1) {
            return StatusValidade.URGENTE;
        } else if (diasRestantes <= 3) {
            return StatusValidade.ATENCAO;
        } else {
            return StatusValidade.NORMAL;
        }
    }

    public Produto adicionarProduto(Produto produto) {
        produto.setCategoria(categoriaService.findById(produto.getCategoria().getId()));
        return produtoRepository.saveAndFlush(produto);
    }

    public void deletarAlimento(Long id) {
        produtoRepository.deleteById(id);
    }

//    public void consumirAlimento(Long id, Integer quantidadeConsumida) throws Exception {
//        Produto idUsuario = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
//        if (idUsuario.getQuantidade() >= quantidadeConsumida) {
//            idUsuario.setQuantidade(idUsuario.getQuantidade() - quantidadeConsumida);
//        } else {
//            throw new Exception("Quantidade insuficiente no estoque.");
//        }
//        produtoRepository.saveAndFlush(idUsuario);
//    }
//

}
