package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Métodos derivados - Spring genera el SQL automáticamente según el nombre
    
    // Buscar empleados con salario mayor a X
    // SELECT * FROM employees WHERE salary > ?
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);
    
    // Buscar empleados cuyo apellido contenga cierto texto
    // SELECT * FROM employees WHERE last_name LIKE '%texto%'
    List<Employee> findByLastNameContaining(String lastName);
    
    // Buscar por email (que es único)
    List<Employee> findByEmail(String email);
    
    // Contar cuántos empleados hay en un departamento
    Long countByDepartmentId(Long departmentId);
}
