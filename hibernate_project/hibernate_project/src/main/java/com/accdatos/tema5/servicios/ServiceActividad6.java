package com.accdatos.tema5.servicios;

import com.accdatos.tema5.pojos.Departamento;
import com.accdatos.tema5.pojos.Sede;
import com.accdatos.tema5.repositorios.DepartamentoRepository;
import com.accdatos.tema5.repositorios.SedeRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ACTIVIDAD 5.6
// Gestiona las excepciones de violacion de restricciones (nombres duplicados)
// Los indices unicos hay que crearlos en MySQL antes de ejecutar esto:
//   CREATE UNIQUE INDEX idx_sede_nombre ON sede(nom_sede);
//   CREATE UNIQUE INDEX idx_depto_nombre_sede ON departamento(nom_depto, id_sede);
@Service
public class ServiceActividad6 {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // intenta crear una sede, si el nombre ya existe captura la excepcion adecuada
    @Transactional
    public void crearSedeConNombre(String nombreSede) {
        System.out.println(">> Intentando crear sede: '" + nombreSede + "'");
        try {
            Sede sede = new Sede();
            sede.setNomSede(nombreSede);
            sedeRepository.saveAndFlush(sede);
            System.out.println("   Sede creada correctamente: " + sede);

        } catch (DataIntegrityViolationException e) {
            // No se puede capturar ConstraintViolationException directamente,
            // hay que recorrer la cadena de causas con getCause() hasta encontrarla
            String mensajeError = obtenerMensajeConstraint(e);
            System.err.println("   ERROR: No se pudo crear la sede.");
            System.err.println("   Motivo: " + mensajeError);
        }
    }

    // intenta crear un departamento, controla si el nombre ya existe en esa sede
    @Transactional
    public void crearDepartamentoEnSede(String nombreDepto, int idSede) {
        System.out.println(">> Intentando crear departamento '" + nombreDepto + "' en sede " + idSede);
        try {
            Sede sede = sedeRepository.findById(idSede)
                    .orElseThrow(() -> new RuntimeException("Sede no encontrada con id: " + idSede));

            Departamento depto = new Departamento();
            depto.setNomDepto(nombreDepto);
            depto.setSede(sede);
            departamentoRepository.saveAndFlush(depto);
            System.out.println("   Departamento creado correctamente: " + depto);

        } catch (DataIntegrityViolationException e) {
            String mensajeError = obtenerMensajeConstraint(e);
            System.err.println("   ERROR: No se pudo crear el departamento.");
            System.err.println("   Motivo: " + mensajeError);
        }
    }

    // recorre la cadena de causas buscando ConstraintViolationException
    // segun el enunciado no se puede capturar directamente, hay que usar getCause() repetidamente
    private String obtenerMensajeConstraint(Exception e) {
        Throwable causa = e;
        while (causa != null) {
            if (causa instanceof ConstraintViolationException cve) {
                // tenemos la causa concreta, devolvemos un mensaje claro y conciso
                return "Violacion de restriccion unica: '" + cve.getConstraintName() +
                       "'. Ya existe un registro con ese valor.";
            }
            causa = causa.getCause();
        }
        // si no encontramos ConstraintViolationException, devolvemos el mensaje generico
        return "Error de integridad de datos: " + e.getMessage();
    }
}
