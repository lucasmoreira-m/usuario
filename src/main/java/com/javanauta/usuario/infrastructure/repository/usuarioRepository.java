package com.javanauta.usuario.infrastructure.repository;


import com.javanauta.usuario.infrastructure.entity.usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<usuario,Long>{

    boolean existsByEmail(String Email);

    Optional<usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
