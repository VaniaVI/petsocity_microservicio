package com.petsocity.petsocity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsocity.petsocity.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
