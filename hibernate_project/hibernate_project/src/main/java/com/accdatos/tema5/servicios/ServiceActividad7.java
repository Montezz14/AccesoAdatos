package com.accdatos.tema5.servicios;

import com.accdatos.tema5.pojos.*;
import com.accdatos.tema5.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

// ACTIVIDAD 5.7
// Crea dos proyectos vinculados a una sede e inserta datos profesionales de empleados
// IMPORTANTE: antes de ejecutar hay que añadir la restriccion unica en MySQL:
//   ALTER TABLE proyecto ADD CONSTRAINT uq_proyecto_nombre UNIQUE (nom_proy);
@Service
public class ServiceActividad7 {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoDatosProfRepository datosProfRepository;

    @Transactional
    public void crearDatos() {
        System.out.println("--- ACTIVIDAD 5.7: CREANDO PROYECTOS Y DATOS PROFESIONALES ---");

        // buscamos la primera sede que haya en la bd para vincular los proyectos
        Sede sede = sedeRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No hay sedes en la base de datos. Ejecuta antes la actividad 5.5."));

        System.out.println(">> Usando sede: " + sede);

        // primer proyecto
        try {
            Proyecto proy1 = new Proyecto("Proyecto Alpha", LocalDate.of(2024, 1, 15));
            proy1.getSedes().add(sede);
            proyectoRepository.saveAndFlush(proy1);
            System.out.println(">> Proyecto 1 creado: " + proy1);
        } catch (DataIntegrityViolationException e) {
            System.err.println(">> ERROR al crear Proyecto Alpha: ya existe un proyecto con ese nombre.");
        }

        // segundo proyecto
        try {
            Proyecto proy2 = new Proyecto("Proyecto Beta", LocalDate.of(2024, 3, 1));
            proy2.getSedes().add(sede);
            proyectoRepository.saveAndFlush(proy2);
            System.out.println(">> Proyecto 2 creado: " + proy2);
        } catch (DataIntegrityViolationException e) {
            System.err.println(">> ERROR al crear Proyecto Beta: ya existe un proyecto con ese nombre.");
        }

        // insertamos datos profesionales para los empleados que existan en la bd
        List<Empleado> empleados = empleadoRepository.findAll();
        String[] categorias = {"A1", "A2", "B1", "B2"};
        double[] sueldos = {35000.0, 28000.0, 42000.0, 31500.0};

        for (int i = 0; i < empleados.size() && i < categorias.length; i++) {
            Empleado emp = empleados.get(i);
            // solo insertamos si no tiene ya datos profesionales
            if (!datosProfRepository.existsById(emp.getDni())) {
                EmpleadoDatosProf datos = new EmpleadoDatosProf(emp, categorias[i], sueldos[i]);
                datosProfRepository.save(datos);
                System.out.println(">> Datos profesionales insertados para: " + emp.getDni());
            } else {
                System.out.println(">> El empleado " + emp.getDni() + " ya tiene datos profesionales, se omite.");
            }
        }

        System.out.println("--- INSERCION ACTIVIDAD 5.7 COMPLETADA ---");
    }

    // verifica el contenido de todas las tablas relevantes
    public void verificarDatosCreados() {
        System.out.println("\n=== VERIFICACION DE DATOS (Actividad 5.7) ===");

        List<Proyecto> proyectos = proyectoRepository.findAll();
        System.out.println(">> Proyectos en BD (" + proyectos.size() + "):");
        proyectos.forEach(p -> System.out.println("   - " + p));

        List<EmpleadoDatosProf> datosList = datosProfRepository.findAll();
        System.out.println(">> Datos profesionales en BD (" + datosList.size() + "):");
        datosList.forEach(d -> System.out.println("   - " + d));

        System.out.println("=== FIN VERIFICACION ===\n");
    }
}
