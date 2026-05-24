package com.accdatos.tema5.repositorios;

import com.accdatos.tema5.pojos.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio de Sede. Al extender JpaRepository, Spring Data genera
// automaticamente los metodos basicos (save, findById, findAll, delete...).
@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
}
