package com.curso.proyectohr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta es la clase principal que inicia toda la aplicación
// Spring detecta automáticamente todas las clases anotadas (@Entity, @Service, etc.)
@SpringBootApplication
public class ProyectoHrSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoHrSpringBootApplication.class, args);
    }
}
