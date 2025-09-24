package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaRequestDto;
import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponseDto criarCategoria(CategoriaRequestDto dto) {
        log.info("Criando nova categoria nome={}", dto.getNome());

        if (categoriaRepository.existsByNome(dto.getNome())) {
            log.error("Erro ao criar categoria novamente. nome={}", dto.getNome());
            throw new RuntimeException("Categoria com o nome [" + dto.getNome() + "] já existe!");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoriaRepository.save(categoria);

        CategoriaResponseDto responseDto = new CategoriaResponseDto();
        responseDto.setId(categoria.getId());
        responseDto.setNome(categoria.getNome());

        log.info("Categoria criada com sucesso id={} nome={}", responseDto.getId(), responseDto.getNome());
        return responseDto;
    }

    public List<CategoriaResponseDto> findAll() {
        log.info("Buscando todas as categorias do banco de dados");

        List<CategoriaResponseDto> dtos = new ArrayList<>();

        for (Categoria categoria : categoriaRepository.findAll()) {
            CategoriaResponseDto dto = new CategoriaResponseDto();
            dto.setId(categoria.getId());
            dto.setNome(categoria.getNome());
            dtos.add(dto);
        }
        log.info("Encontradas {} categorias", dtos.size());
        return dtos;
    }

    public Categoria findById(Long id) {
        log.debug("Buscando categoria por id={}", id);
        Categoria categoriaId = categoriaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Categoria não encontrada!"));

        log.debug("Categoria encontrada id={} nome={}", categoriaId.getId(), categoriaId.getNome());
        return categoriaId;
    }

    public void deletarPorId(Long id) {
        log.info("Iniciando deleção da categoria id={}", id);
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        if(categoria.getProduto() != null && categoria.getProduto().isEmpty()){
            log.error("Tentativa de deletar categoria com produtos associados id={}", id);
            throw new RuntimeException("Não é possível deletar categoria que possui produtos associados!");
        }

        categoriaRepository.deleteById(id);
        log.info("Categoria deletada com sucesso id={}", id);
    }
}
