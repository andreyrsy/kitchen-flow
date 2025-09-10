package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.ProdutoDto;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }


//    public StatusValidade calcularStatus(LocalDate dataValidade) {
//        LocalDate dataAtual = LocalDate.now();
//        long diasRestantes = ChronoUnit.DAYS.between(dataAtual, dataValidade);
//
//        if (diasRestantes < 0) {
//            return StatusValidade.VENCIDO;
//        } else if (diasRestantes <= 1) {
//            return StatusValidade.URGENTE;
//        } else if (diasRestantes <= 3) {
//            return StatusValidade.ATENCAO;
//        } else {
//            return StatusValidade.NORMAL;
//        }
//    }
//
//    public Produto adicionarProduto(Produto produto) {
//        return produtoRepository.saveAndFlush(produto);
//    }
//
//    public List<ProdutoDto> listarAlimentos() {
//        List<Produto> alimentosDoBanco = produtoRepository.findAll();
//        List<ProdutoDto> dtosParaEnviar = new ArrayList<>();
//
//        for(Produto produto : alimentosDoBanco){
//            StatusValidade statusValidade = calcularStatus(produto.getDataValidade());
//
//            ProdutoDto dto = new ProdutoDto();
//
//            dto.setId(produto.getId());
//            dto.setAlimento(produto.getNome());
//            dto.setQuantidade(produto.getUnidadeMedida());
//
//            dto.setStatus(statusValidade);
//            dtosParaEnviar.add(dto);
//        }
//        return dtosParaEnviar;
//    }
//
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
//    public void deletarAlimento(Long id) {
//        produtoRepository.deleteById(id);
//    }
}
