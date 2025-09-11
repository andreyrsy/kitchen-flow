package dev.andreyrsy.kitchen.flow.service;

import dev.andreyrsy.kitchen.flow.model.Categoria;
import dev.andreyrsy.kitchen.flow.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria criarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deletarPorId(Long id){
        categoriaRepository.deleteById(id);
    }
}
