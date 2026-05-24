package com.accdatos.tema5.repositorios;

import com.accdatos.tema5.pojos.EmpleadoDatosProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// La clave primaria es el DNI (String), compartida con Empleado
@Repository
public interface EmpleadoDatosProfRepository extends JpaRepository<EmpleadoDatosProf, String> {
}
