package com.accdatos.tema5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del proyecto HIBERNATE_PROJECT.
 * La anotacion @SpringBootApplication arranca todo el contexto de Spring,
 * habilita la autoconfiguracion y el escaneo de componentes del paquete
 * com.accdatos.tema5 y sus subpaquetes (pojos, repositorios, servicios, util).
 */
@SpringBootApplication
public class AccesoADatosTema5Application {

    public static void main(String[] args) {
        SpringApplication.run(AccesoADatosTema5Application.class, args);
    }
}
