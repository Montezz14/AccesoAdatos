package com.accdatos.tema5.repositorios;

import com.accdatos.tema5.pojos.EmpleadoDatosProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDatosProfRepository extends JpaRepository<EmpleadoDatosProf, String> {
}
