package com.accdatos.tema5.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * ACTIVIDAD 5.3
 * Esta clase sirve como una prueba de arranque para verificar la conexion a la base de datos.
 * Al implementar CommandLineRunner, el metodo "run" se ejecutara automaticamente
 * despues de que la aplicacion Spring Boot se haya iniciado.
 *
 * NOTA: dejar activa esta clase solo para la actividad 5.3. Para las actividades
 * siguientes conviene comentar la anotacion @Component o el cuerpo del metodo run,
 * de modo que solo se ejecute el AppRunner principal.
 */
@Component // Le dice a Spring que esta clase es un componente que debe gestionar
public class VerificadorConexion implements CommandLineRunner {

    // @Autowired le pide a Spring que nos "inyecte" o proporcione el objeto DataSource
    // que ya ha sido configurado automaticamente a partir de application.properties
    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("========================================================================");
        System.out.println("INICIANDO PRUEBA DE CONEXION A LA BASE DE DATOS...");
        System.out.println("========================================================================");

        // Usamos un bloque try-with-resources para asegurar que la conexion se cierre siempre.
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                System.out.println("¡CONEXION ESTABLECIDA CON EXITO!");

                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(" -> URL de la BBDD: " + metaData.getURL());
                System.out.println(" -> Usuario: " + metaData.getUserName());
                System.out.println(" -> Driver: " + metaData.getDriverName());

                System.out.println("\n--- OBTENIENDO ESQUEMA DE TABLAS ---");
                // Obtenemos las tablas del catalogo/esquema actual.
                ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%",
                        new String[]{"TABLE"});

                boolean hayTablas = false;
                while (tables.next()) {
                    hayTablas = true;
                    System.out.println("   - Tabla encontrada: " + tables.getString("TABLE_NAME"));
                }
                if (!hayTablas) {
                    System.out.println("   (Todavia no hay tablas creadas en la base de datos)");
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: No se ha podido establecer la conexion con la base de datos.");
            System.err.println("Detalle: " + e.getMessage());
        }

        System.out.println("========================================================================");
        System.out.println("FIN DE LA PRUEBA DE CONEXION.");
        System.out.println("========================================================================");
    }
}
