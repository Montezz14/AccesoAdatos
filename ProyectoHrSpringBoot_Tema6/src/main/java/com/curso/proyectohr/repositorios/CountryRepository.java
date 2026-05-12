package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    // Country usa String como ID, por eso el segundo genérico es String
}
