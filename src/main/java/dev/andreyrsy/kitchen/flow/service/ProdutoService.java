package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.model.StatusValidade;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public Produto criarProduto(Produto produto) {
        log.info("Criando produto nome={} categoriaId={}", produto.getNome(), produto.getCategoria().getId());

        try {
            Categoria categoriaSelecionada = categoriaService.findById(produto.getCategoria().getId());
            produto.setCategoria(categoriaSelecionada);
            log.debug("Produto processado nome={}", produto.getNome());
            return produtoRepository.saveAndFlush(produto);
        } catch (Exception ex) {
            log.error("LOG: Falha ao criar produto com nome: {}", produto.getNome());
            throw new RuntimeException("Falha ao criar produto", ex);
        }
    }

    public List<ProdutoResponseDto> listarProdutos() {
        log.info("Buscando todos os produtos do banco de dados");
        List<ProdutoResponseDto> produtos = new ArrayList<>();
        try {
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
                produtoResponseDto.setCategoriaDto(categorias.getFirst());

                produtos.add(produtoResponseDto);
            }
        } catch (Exception ex) {
            log.error("LOG: Falha ao tentar listar produtos", ex);
            throw new RuntimeException("Falha ao listar produtos");
        }
        log.info("Encontrados {} produtos", produtos.size());
        return produtos;
    }

    public Produto findById(Long id) {
        log.debug("Buscando produto por id={}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));

        log.debug("Produto encontrado id={} nome={}", produto.getId(), produto.getNome());

        return produto;
    }

    public StatusValidade calcularStatus(LocalDate dataValidade) {
        log.debug("Calculando status de validade para data={}", dataValidade);
        LocalDate dataAtual = LocalDate.now();
        long diasRestantes = ChronoUnit.DAYS.between(dataAtual, dataValidade);

        StatusValidade status;
        if (diasRestantes < 0) {
            status = StatusValidade.VENCIDO;
        } else if (diasRestantes <= 1) {
            status = StatusValidade.URGENTE;
        } else if (diasRestantes <= 3) {
            status = StatusValidade.ATENCAO;
        } else {
            status = StatusValidade.NORMAL;
        }
        log.debug("Status calculado: {} para {} dias restantes", status, diasRestantes);
        return status;
    }

    public void deletarProduto(Long id) {
        log.info("Iniciando deleção do produto id={}", id);
        produtoRepository.deleteById(id);
        log.info("Produto deletado com sucesso id={}", id);
    }
}
