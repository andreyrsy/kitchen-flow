package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.ProdutoDto;
import dev.andreyrsy.kitchen.flow.model.ProdutoModel;
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

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
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

    public ProdutoModel adicionarAlimento(ProdutoModel produtoModel) {
        return produtoRepository.saveAndFlush(produtoModel);
    }

    public List<ProdutoDto> listarAlimentos() {
        List<ProdutoModel> alimentosDoBanco = produtoRepository.findAll();
        List<ProdutoDto> dtosParaEnviar = new ArrayList<>();

        for(ProdutoModel alimento : alimentosDoBanco){
            StatusValidade statusValidade = calcularStatus(alimento.getDataValidade());

            ProdutoDto dto = new ProdutoDto();

            dto.setId(alimento.getId());
            dto.setAlimento(alimento.getAlimento());
            dto.setQuantidade(alimento.getQuantidade());
            dto.setDataValidade(alimento.getDataValidade());

            dto.setStatus(statusValidade);
            dtosParaEnviar.add(dto);
        }
        return dtosParaEnviar;
    }

    public void consumirAlimento(Long id, Integer quantidadeConsumida) throws Exception {
        ProdutoModel idUsuario = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
        if (idUsuario.getQuantidade() >= quantidadeConsumida) {
            idUsuario.setQuantidade(idUsuario.getQuantidade() - quantidadeConsumida);
        } else {
            throw new Exception("Quantidade insuficiente no estoque.");
        }
        produtoRepository.saveAndFlush(idUsuario);
    }

    public void deletarAlimento(Long id) {
        produtoRepository.deleteById(id);
    }
}
