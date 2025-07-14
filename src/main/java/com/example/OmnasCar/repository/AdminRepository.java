package com.example.OmnasCar.repository;

import com.example.OmnasCar.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}