package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository indica que esta es una interfaz de acceso a datos
// JpaRepository<Region, Long> proporciona los métodos CRUD básicos
// Region es la entidad, Long es el tipo del ID
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // JpaRepository ya nos da: findAll(), findById(), save(), delete(), count(), etc.
    // Aquí podríamos añadir más métodos personalizados si los necesitamos
}
