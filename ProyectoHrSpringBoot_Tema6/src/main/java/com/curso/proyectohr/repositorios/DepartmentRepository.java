package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Métodos que podríamos añadir:
    // findByDepartmentName(String name)
    // findByLocationId(Long locationId)
}
