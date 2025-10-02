package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.ProdutoRequestDto;
import dev.andreyrsy.kitchen.flow.dto.ProdutoResponseDto;
import dev.andreyrsy.kitchen.flow.mapper.ProdutoMapper;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.model.Produto;
import dev.andreyrsy.kitchen.flow.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;
    private final ProdutoMapper mapper;


    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService, ProdutoMapper mapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
        this.mapper = mapper;
    }

    public ProdutoResponseDto criarProduto(ProdutoRequestDto dtoRequest) {
        log.info("Criando produto nome={} categoriaId={}", dtoRequest.getNome(), dtoRequest.getCategoriaId());

        try {
            if (produtoRepository.existsByNome(dtoRequest.getNome())) {
                log.error("Tentativa de criar produto duplicado nome={}", dtoRequest.getNome());
                throw new RuntimeException("Produto existente!");
            }

            Categoria categoriaSelecionada = categoriaService.findById(dtoRequest.getCategoriaId());
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
        log.debug("Buscando produto por id={}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));

        log.debug("Produto encontrado id={} nome={}", produto.getId(), produto.getNome());

        return produto;
    }

    public void deletarProduto(Long id) {
        log.info("Iniciando deleção do produto id={}", id);
        produtoRepository.deleteById(id);
        log.info("Produto deletado com sucesso id={}", id);
    }
}
