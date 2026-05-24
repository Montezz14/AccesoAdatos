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

import java.util.List;

// ACTIVIDAD 5.5
// Crea una sede nueva, dos departamentos y dos empleados por departamento (4 en total)
// Incluye un metodo para verificar que los datos se han creado correctamente
@Service
public class ServiceActividad5 {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Transactional
    public void crearDatos() {
        System.out.println("--- ACTIVIDAD 5.5: INSERTANDO DATOS ---");

        // creamos la sede nueva
        Sede sede = new Sede();
        sede.setNomSede("Barcelona Hub");
        Sede sedeGuardada = sedeRepository.saveAndFlush(sede);
        System.out.println(">> Sede creada: " + sedeGuardada);

        // primer departamento
        Departamento depto1 = new Departamento();
        depto1.setNomDepto("Recursos Humanos");
        depto1.setSede(sedeGuardada);
        Departamento deptoGuardado1 = departamentoRepository.saveAndFlush(depto1);
        System.out.println(">> Departamento 1 creado: " + deptoGuardado1);

        // segundo departamento
        Departamento depto2 = new Departamento();
        depto2.setNomDepto("Contabilidad");
        depto2.setSede(sedeGuardada);
        Departamento deptoGuardado2 = departamentoRepository.saveAndFlush(depto2);
        System.out.println(">> Departamento 2 creado: " + deptoGuardado2);

        // dos empleados para el primer departamento
        Empleado emp1 = new Empleado("11111111A", "Carlos Ruiz", deptoGuardado1);
        Empleado emp2 = new Empleado("22222222B", "Laura Martinez", deptoGuardado1);
        empleadoRepository.save(emp1);
        empleadoRepository.save(emp2);
        System.out.println(">> Empleados creados en " + deptoGuardado1.getNomDepto() + ": " + emp1 + ", " + emp2);

        // dos empleados para el segundo departamento
        Empleado emp3 = new Empleado("33333333C", "Pedro Sanchez", deptoGuardado2);
        Empleado emp4 = new Empleado("44444444D", "Maria Garcia", deptoGuardado2);
        empleadoRepository.save(emp3);
        empleadoRepository.save(emp4);
        System.out.println(">> Empleados creados en " + deptoGuardado2.getNomDepto() + ": " + emp3 + ", " + emp4);

        System.out.println("--- INSERCION COMPLETADA ---");
    }

    // metodo de verificacion pedido en el enunciado, comprueba el contenido de las tablas
    public void verificarDatosCreados() {
        System.out.println("\n=== VERIFICACION DE DATOS (Actividad 5.5) ===");

        List<Sede> sedes = sedeRepository.findAll();
        System.out.println(">> Sedes en BD (" + sedes.size() + "):");
        sedes.forEach(s -> System.out.println("   - " + s));

        List<Departamento> deptos = departamentoRepository.findAll();
        System.out.println(">> Departamentos en BD (" + deptos.size() + "):");
        deptos.forEach(d -> System.out.println("   - " + d));

        List<Empleado> empleados = empleadoRepository.findAll();
        System.out.println(">> Empleados en BD (" + empleados.size() + "):");
        empleados.forEach(e -> System.out.println("   - " + e));

        System.out.println("=== FIN VERIFICACION ===\n");
    }
}
