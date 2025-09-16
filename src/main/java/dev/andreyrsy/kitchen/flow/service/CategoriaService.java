package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.dto.CategoriaResponseDto;
import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(Long id) {
        Categoria categoriaId = categoriaRepository.findById(id).get();
        return categoriaId;
    }

    public List<CategoriaResponseDto> findAll(){
        List<CategoriaResponseDto> dtos = new ArrayList<>();

        for(Categoria categoria : categoriaRepository.findAll()){
            CategoriaResponseDto dto = new CategoriaResponseDto();
            dto.setId(categoria.getId());
            dto.setNome(categoria.getNome());
            dtos.add(dto);
        }
        return dtos;
    }

    public Categoria criarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deletarPorId(Long id){
        categoriaRepository.deleteById(id);
    }
}
