package com.accdatos.tema5.repositorios;

import com.accdatos.tema5.pojos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// La clave primaria de Empleado es el DNI (String)
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
}
