package com.accdatos.tema5.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runner principal de la aplicacion.
 * Implementa CommandLineRunner, por lo que su metodo run() se ejecuta
 * automaticamente una vez que Spring Boot ha arrancado.
 *
 * Desde aqui se invocan los servicios de cada actividad y, justo despues,
 * el metodo verificarDatosCreados() correspondiente, tal y como piden los
 * enunciados (la verificacion debe lanzarse "desde el runner").
 *
 * Para ejecutar una actividad concreta basta con descomentar su bloque y
 * dejar comentados los demas, evitando asi insertar datos duplicados.
 */
@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private SetupInicialService setupInicialService;   // Actividad 5.4

    @Autowired
    private ServiceActividad5 serviceActividad5;        // Actividad 5.5

    @Autowired
    private ServiceActividad6 serviceActividad6;        // Actividad 5.6

    @Autowired
    private ServiceActividad7 serviceActividad7;        // Actividad 5.7

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n############# EJECUCION DEL RUNNER PRINCIPAL #############\n");

        // ---------- ACTIVIDAD 5.4: crear una sede, un departamento y un empleado ----------
        // setupInicialService.crearDatosDeEjemplo();

        // ---------- ACTIVIDAD 5.5: una sede, dos departamentos y dos empleados por departamento ----------
        serviceActividad5.crearDatos();
        serviceActividad5.verificarDatosCreados();

        // ---------- ACTIVIDAD 5.6: provocar y gestionar la excepcion de nombre duplicado ----------
        // Estas llamadas demuestran que los indices unicos lanzan la excepcion y que se gestiona.
        // serviceActividad6.crearSedeConNombre("Barcelona Hub");      // duplicada -> excepcion controlada
        // serviceActividad6.crearDepartamentoEnSede("Recursos Humanos", 1); // duplicado en la misma sede

        // ---------- ACTIVIDAD 5.7: crear proyectos e insertar datos profesionales ----------
        // serviceActividad7.crearDatos();
        // serviceActividad7.verificarDatosCreados();

        System.out.println("\n############# FIN DEL RUNNER PRINCIPAL #############\n");
    }
}
