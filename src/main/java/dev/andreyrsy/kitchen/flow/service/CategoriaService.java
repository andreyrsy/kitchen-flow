package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
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

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criarCategoria(Categoria categoria) {
        log.info("Criando nova categoria nome={}", categoria.getNome());
        Categoria categoriaCriada = categoriaRepository.save(categoria);

        log.info("Categoria criada com sucesso id={} nome={}", categoriaCriada.getId(), categoriaCriada.getNome());
        return categoriaCriada;
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
        Categoria categoriaId = categoriaRepository.findById(id).get();
        log.debug("Categoria encontrada id={} nome={}", categoriaId.getId(), categoriaId.getNome());
        return categoriaId;
    }

    public void deletarPorId(Long id) {
        log.info("Iniciando deleção da categoria id={}", id);
        categoriaRepository.deleteById(id);
        log.info("Categoria deletada com sucesso id={}", id);
    }
}
