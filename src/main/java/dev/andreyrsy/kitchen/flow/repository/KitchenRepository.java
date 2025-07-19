package dev.andreyrsy.kitchen.flow.repository;

import dev.andreyrsy.kitchen.flow.model.KitchenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<KitchenModel, Long> {
}
