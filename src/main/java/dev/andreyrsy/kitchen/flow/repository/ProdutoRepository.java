package dev.andreyrsy.kitchen.flow.repository;

import dev.andreyrsy.kitchen.flow.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
