package dev.andreyrsy.kitchen.flow.repository;

import dev.andreyrsy.kitchen.flow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
