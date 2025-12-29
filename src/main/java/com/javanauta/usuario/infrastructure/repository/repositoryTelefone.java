package com.javanauta.usuario.infrastructure.repository;

import com.java.aprendendospring.infrastructure.entity.telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryTelefone extends JpaRepository<telefone, Long> {

}
