package com.accdatos.tema5.repositorios;

import com.accdatos.tema5.pojos.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    // Spring genera automaticamente los metodos basicos (save, findById, findAll...)
}
