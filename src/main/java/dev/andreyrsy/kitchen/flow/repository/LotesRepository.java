package dev.andreyrsy.kitchen.flow.repository;

import dev.andreyrsy.kitchen.flow.model.Lotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotesRepository extends JpaRepository<Lotes, Long> {
}
