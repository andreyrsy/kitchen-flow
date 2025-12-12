package dev.andreyrsy.orderly.repository;

import dev.andreyrsy.orderly.model.Lotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotesRepository extends JpaRepository<Lotes, Long> {
}
