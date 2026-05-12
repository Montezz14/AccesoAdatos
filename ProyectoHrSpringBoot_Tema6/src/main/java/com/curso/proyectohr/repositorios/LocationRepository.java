package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Métodos de búsqueda derivados (Spring los crea automáticamente)
    // Podría añadir: findByCity(String city), etc.
}
