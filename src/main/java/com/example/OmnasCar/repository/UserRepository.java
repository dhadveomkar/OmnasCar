package com.example.OmnasCar.repository;

import com.example.OmnasCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
    Optional<User> findByEmail(String email);
}