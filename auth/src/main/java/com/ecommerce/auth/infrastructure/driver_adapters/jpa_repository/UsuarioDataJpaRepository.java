package com.ecommerce.auth.infrastructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDataJpaRepository extends JpaRepository<UsuarioData, Long> {
    // Crear consulta buscar por email
    Optional<UsuarioData> findByEmail(String email);
}
