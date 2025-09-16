package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }


    public List<ProdutoResponseDto> listarProdutos() {
        List<ProdutoResponseDto> produtos = new ArrayList<>();

        for (Produto produto : produtoRepository.findAll()) {
            ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
            CategoriaResponseDto categoriaDto = new CategoriaResponseDto();
            List<CategoriaResponseDto> categorias = new ArrayList<>();
            categoriaDto.setId(produto.getCategoria().getId());
            categoriaDto.setNome(produto.getCategoria().getNome());
            categorias.add(categoriaDto);

            produtoResponseDto.setId(produto.getId());
            produtoResponseDto.setNome(produto.getNome());
            produtoResponseDto.setUnidade_medida(produto.getUnidadeMedida());
            produtoResponseDto.setCategoriaDto(categorias.get(0));

            produtos.add(produtoResponseDto);
        }
        return produtos;
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

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
    }

}
