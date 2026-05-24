package com.accdatos.tema5.servicios;

import com.accdatos.tema5.pojos.Departamento;
import com.accdatos.tema5.pojos.Empleado;
import com.accdatos.tema5.pojos.Sede;
import com.accdatos.tema5.repositorios.DepartamentoRepository;
import com.accdatos.tema5.repositorios.EmpleadoRepository;
import com.accdatos.tema5.repositorios.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ACTIVIDAD 5.4
// Servicio que crea una sede, un departamento y un empleado de ejemplo
@Service
public class SetupInicialService {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // @Transactional garantiza que si algo falla, se deshace todo (rollback)
    @Transactional
    public void crearDatosDeEjemplo() {
        System.out.println("--- ACTIVIDAD 5.4: CREANDO DATOS DE EJEMPLO ---");

        // 1. Creamos la sede y la guardamos para obtener su ID autogenerado
        Sede sede = new Sede();
        sede.setNomSede("Madrid Central");
        Sede sedeGuardada = sedeRepository.saveAndFlush(sede);
        System.out.println(">> Sede creada: " + sedeGuardada);

        // 2. Creamos el departamento vinculado a esa sede
        Departamento depto = new Departamento();
        depto.setNomDepto("Tecnologia");
        depto.setSede(sedeGuardada);
        Departamento deptoGuardado = departamentoRepository.saveAndFlush(depto);
        System.out.println(">> Departamento creado: " + deptoGuardado);

        // 3. Creamos el empleado vinculado al departamento
        Empleado emp = new Empleado();
        emp.setDni("12345678A");
        emp.setNomEmp("Ana Lopez");
        emp.setDepartamento(deptoGuardado);
        empleadoRepository.save(emp);
        System.out.println(">> Empleado creado: " + emp);

        System.out.println("--- DATOS DE EJEMPLO CREADOS CON EXITO ---");
    }
}
