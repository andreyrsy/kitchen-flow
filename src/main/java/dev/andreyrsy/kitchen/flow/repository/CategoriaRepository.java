package dev.andreyrsy.kitchen.flow.repository;

import dev.andreyrsy.kitchen.flow.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNome(String nome);
}
