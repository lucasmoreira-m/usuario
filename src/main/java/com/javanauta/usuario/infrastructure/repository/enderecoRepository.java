package com.javanauta.usuario.infrastructure.repository;

import com.java.aprendendospring.infrastructure.entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface enderecoRepository extends JpaRepository<Enderecos, Long> {

}
