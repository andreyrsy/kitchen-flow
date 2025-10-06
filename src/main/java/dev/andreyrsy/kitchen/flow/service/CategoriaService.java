package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.exception.business.CategoriaNaoEncontradaException;
import dev.andreyrsy.kitchen.flow.mapper.CategoriaMapper;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper mapper) {
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    public CategoriaResponseDto criarCategoria(CategoriaRequestDto dto) {
        log.info("Criando nova categoria nome={}", dto.getNome());

        if (categoriaRepository.existsByNome(dto.getNome())) {
            log.error("Erro ao criar categoria novamente. nome={}", dto.getNome());
            throw new RuntimeException("Categoria com o nome [" + dto.getNome() + "] já existe!");
        }
        Categoria categoria = mapper.toEntity(dto);
        categoriaRepository.save(categoria);

        CategoriaResponseDto responseDto = mapper.toResponseDto(categoria);

        log.info("Categoria criada com sucesso id={} nome={}", responseDto.getId(), responseDto.getNome());
        return responseDto;
    }

    public List<CategoriaResponseDto> findAll() {
        log.info("Buscando todas as categorias do banco de dados");

        List<CategoriaResponseDto> dtos = new ArrayList<>();

        for (Categoria categoria : categoriaRepository.findAll()) {
            CategoriaResponseDto dto = mapper.toResponseDto(categoria);
            dtos.add(dto);
        }
        log.info("Encontradas {} categorias", dtos.size());
        return dtos;
    }

    public Categoria findById(Long id) {
        log.info("Buscando categoria por id={}", id);
        Categoria categoriaId = categoriaRepository.findById(id).orElseThrow(() ->
                new CategoriaNaoEncontradaException(id));

        log.info("Categoria encontrada id={} nome={}", categoriaId.getId(), categoriaId.getNome());
        return categoriaId;
    }

    public void deletarPorId(Long id) {
        log.info("Iniciando deleção da categoria id={}", id);
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        if (categoria.getProduto() != null && !categoria.getProduto().isEmpty()) {
            log.error("Tentativa de deletar categoria com produtos associados id={}", id);
            throw new RuntimeException("Não é possível deletar categoria que possui produtos associados.");
        }
        categoriaRepository.deleteById(id);
        log.info("Categoria deletada com sucesso id={}", id);
    }
}
