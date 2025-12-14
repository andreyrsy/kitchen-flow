package dev.andreyrsy.orderly.repository;

import dev.andreyrsy.orderly.model.Lotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotesRepository extends JpaRepository<Lotes, Long> {
}
