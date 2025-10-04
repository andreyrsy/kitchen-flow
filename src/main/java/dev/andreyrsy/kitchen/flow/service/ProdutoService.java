package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.exception.business.CategoriaNaoEncontradaException;
import dev.andreyrsy.kitchen.flow.exception.business.LoteNaoEncontradoException;
import dev.andreyrsy.kitchen.flow.exception.business.ProdutoDuplicadoException;
import dev.andreyrsy.kitchen.flow.exception.business.ProdutoNaoEncontradoException;
import dev.andreyrsy.kitchen.flow.mapper.ProdutoMapper;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.repository.CategoriaRepository;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper mapper;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ProdutoMapper mapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    public ProdutoResponseDto criarProduto(ProdutoRequestDto dtoRequest) {
        log.info("Criando produto nome={} categoriaId={}", dtoRequest.getNome(), dtoRequest.getCategoriaId());

        try {
            if (produtoRepository.existsByNome(dtoRequest.getNome())) {
                log.error("Tentativa de criar produto duplicado nome={}", dtoRequest.getNome());
                throw new ProdutoDuplicadoException(dtoRequest.getNome());
            }

            Categoria categoriaSelecionada = categoriaRepository.findById(dtoRequest.getCategoriaId()).orElseThrow(() -> new CategoriaNaoEncontradaException(dtoRequest.getCategoriaId()));
            Produto entity = mapper.toEntity(dtoRequest);
            entity.setCategoria(categoriaSelecionada);

            Produto produtoSalvo = produtoRepository.save(entity);

            ProdutoResponseDto responseDto = mapper.toDto(produtoSalvo);

            log.info("Produto processado nome={}", dtoRequest.getNome());
            return responseDto;
        } catch (Exception ex) {
            log.error("Falha ao criar produto com nome: {}", dtoRequest.getNome());
            throw new RuntimeException("Falha ao criar produto", ex);
        }
    }

    public List<ProdutoResponseDto> listarProdutos() {
        log.info("Buscando todos os produtos do banco de dados");
        List<Produto> produtosList = produtoRepository.findAll();
        try {
            List<ProdutoResponseDto> produtos = mapper.toDtoList(produtosList);
            log.info("Encontrados {} produtos", produtos.size());
            return produtos;
        } catch (Exception ex) {
            log.error("Falha ao tentar listar produtos", ex);
            throw new RuntimeException("Falha ao listar produtos");
        }
    }

    public Produto findById(Long id) {
        log.info("Buscando produto por id={}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        log.info("Produto encontrado id={} nome={}", produto.getId(), produto.getNome());

        return produto;
    }

    public void deletarProduto(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        try {
            produtoRepository.deleteById(id);
            log.info("Lote apagado com sucesso id={}", id);
        } catch (Exception ex) {
            log.error("Falha ao deletar lote id={}!", id, ex);
            throw new ProdutoNaoEncontradoException(id);
        }
        log.info("Lote deletado com sucesso id={}", id);
    }
}
