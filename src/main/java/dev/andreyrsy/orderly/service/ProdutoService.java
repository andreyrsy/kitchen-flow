package dev.andreyrsy.orderly.service;

import dev.andreyrsy.orderly.dto.request.ProdutoRequestDto;
import dev.andreyrsy.orderly.dto.response.ProdutoResponseDto;
import dev.andreyrsy.orderly.exception.business.CategoriaNaoEncontradaException;
import dev.andreyrsy.orderly.exception.business.ProdutoDuplicadoException;
import dev.andreyrsy.orderly.exception.business.ProdutoInvalidoException;
import dev.andreyrsy.orderly.exception.business.ProdutoNaoEncontradoException;
import dev.andreyrsy.orderly.mapper.ProjectMapper;
import dev.andreyrsy.orderly.model.Categoria;
import dev.andreyrsy.orderly.model.Produto;
import dev.andreyrsy.orderly.repository.CategoriaRepository;
import dev.andreyrsy.orderly.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProjectMapper projectMapper;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
            ProjectMapper projectMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.projectMapper = projectMapper;
    }

    @Transactional
    public ProdutoResponseDto criarProduto(ProdutoRequestDto dtoRequest) {
        log.info("Criando produto nome={} categoriaId={}", dtoRequest.getNome(), dtoRequest.getCategoriaId());

        try {
            if (produtoRepository.existsByNome(dtoRequest.getNome())) {
                log.error("Tentativa de criar produto duplicado nome={}", dtoRequest.getNome());
                throw new ProdutoDuplicadoException(dtoRequest.getNome());
            }

            Categoria categoriaSelecionada = categoriaRepository.findById(dtoRequest.getCategoriaId())
                    .orElseThrow(() -> new CategoriaNaoEncontradaException(dtoRequest.getCategoriaId()));
            Produto entity = projectMapper.toProdutoEntity(dtoRequest);

            entity.setCategoria(categoriaSelecionada);
            Produto produtoSalvo = produtoRepository.save(entity);

            ProdutoResponseDto responseDto = projectMapper.toProdutoResponseDto(produtoSalvo);
            log.info("Produto processado nome={}", dtoRequest.getNome());
            return responseDto;
        } catch (Exception ex) {
            log.error("Falha ao criar produto com nome: {}", dtoRequest.getNome());
            throw new ProdutoInvalidoException(dtoRequest.getNome());
        }
    }

    public List<ProdutoResponseDto> listarProdutos() {
        log.info("Buscando todos os produtos do banco de dados");
        List<Produto> produtosList = produtoRepository.findAll();
        try {
            List<ProdutoResponseDto> produtos = projectMapper.toProdutoDtoList(produtosList);
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

    @Transactional
    public void deletarProduto(Long id) {
        log.info("Iniciando deleção do lote id={}", id);
        try {
            produtoRepository.deleteById(id);
            log.info("Lotes apagado com sucesso id={}", id);
        } catch (Exception ex) {
            log.error("Falha ao deletar lote id={}!", id, ex);
            throw new ProdutoNaoEncontradoException(id);
        }
        log.info("Lotes deletado com sucesso id={}", id);
    }
}
