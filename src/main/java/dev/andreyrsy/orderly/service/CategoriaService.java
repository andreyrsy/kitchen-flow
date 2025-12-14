package dev.andreyrsy.orderly.service;

import dev.andreyrsy.orderly.dto.request.CategoriaRequestDto;
import dev.andreyrsy.orderly.dto.response.CategoriaResponseDto;
import dev.andreyrsy.orderly.exception.business.CategoriaDuplicadaException;
import dev.andreyrsy.orderly.exception.business.CategoriaNaoEncontradaException;
import dev.andreyrsy.orderly.mapper.ProjectMapper;
import dev.andreyrsy.orderly.model.Categoria;
import dev.andreyrsy.orderly.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ProjectMapper projectMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, ProjectMapper projectMapper) {
        this.categoriaRepository = categoriaRepository;
        this.projectMapper = projectMapper;
    }

    public CategoriaResponseDto criarCategoria(CategoriaRequestDto dto) {
        log.info("Criando nova categoria nome={}", dto.getNome());

        if (categoriaRepository.existsByNome(dto.getNome())) {
            log.error("Erro ao criar categoria novamente. nome={}", dto.getNome());
            throw new CategoriaDuplicadaException(dto.getNome());
        }

        Categoria categoria = projectMapper.toCategoriaEntity(dto);
        categoriaRepository.save(categoria);

        CategoriaResponseDto responseDto = projectMapper.toCategoriaResponseDto(categoria);

        log.info("Categoria criada com sucesso id={} nome={}", responseDto.getId(), responseDto.getNome());
        return responseDto;
    }

    public List<CategoriaResponseDto> findAll() {
        log.info("Buscando todas as categorias do banco de dados");

        List<CategoriaResponseDto> dtos = new ArrayList<>();

        for (Categoria categoria : categoriaRepository.findAll()) {
            CategoriaResponseDto dto = projectMapper.toCategoriaResponseDto(categoria);
            dtos.add(dto);
        }
        log.info("Encontradas {} categorias", dtos.size());
        return dtos;
    }

    public Categoria findById(Long id) {
        log.info("Buscando categoria por id={}", id);
        Categoria categoriaId = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        log.info("Categoria encontrada id={} nome={}", categoriaId.getId(), categoriaId.getNome());
        return categoriaId;
    }

    public void deletarPorId(Long id) {
        log.info("Iniciando deleção da categoria id={}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        if (categoria.getProduto() != null && !categoria.getProduto().isEmpty()) {
            log.error("Tentativa de deletar categoria com produtos associados id={}", id);
            throw new RuntimeException("Não é possível deletar categoria que possui produtos associados.");
        }
        categoriaRepository.deleteById(id);
        log.info("Categoria deletada com sucesso id={}", id);
    }
}
