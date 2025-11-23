package id.monterojorge;

import id.monterojorge.dao.DatabaseConnection;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class NavegadorTabla {

    // --- ConfiguraciÃ³n de la conexiÃ³n a la base de datos ---
    // Â¡Â¡Â¡IMPORTANTE!!! Modifica estos valores para que coincidan con tu configuraciÃ³n de MySQL.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASS = "tu_contraseÃ±a";

    public static void main(String[] args) {
        // 1. Comprobar que se ha pasado el nombre de la tabla como argumento.
        if (args.length == 0) {
            System.out.println("Error: Debes proporcionar el nombre de la tabla como argumento.");
            System.out.println("Uso: java NavegadorTabla <nombre_tabla>");
            return;
        }
        String nombreTabla = args[0];

        // 2. Usar try-with-resources para garantizar que los recursos se cierren automÃ¡ticamente.
        // Esta es una de las mejores prÃ¡cticas en Java moderno para el manejo de recursos.
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + nombreTabla)) {

            if (connection == null) {
                throw new Exception("Error al obtener la conexiÃ³n a la base de datos.");
            }

            System.out.println("Hemos obtenido la conexiÃ³n a la base de datos");

            System.out.println("ConexiÃ³n establecida. Navegando por la tabla '" + nombreTabla + "'.");
            System.out.println("Comandos: 'k' (siguiente), 'd' (anterior), <numero> (ir a fila), '.' (salir)");
            System.out.println("-----------------------------------------------------------------");

            if (rs.first()) { // Moverse a la primera fila y comprobar si hay datos.
                mostrarFilaActual(rs);
                navegacionInteractiva(rs);
            } else {
                System.out.println("La tabla '" + nombreTabla + "' estÃ¡ vacÃ­a.");
            }

        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Programa finalizado. Recursos liberados.");
    }

    /**
     * Gestiona el bucle de comandos del usuario para navegar por el ResultSet.
     * @param rs El ResultSet sobre el que navegar.
     * @throws Exception Si ocurre un error de entrada/salida o SQL.
     */
    private static void navegacionInteractiva(ResultSet rs) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String comando;

        // Bucle principal que lee y procesa los comandos del usuario.
        while (!(comando = br.readLine()).equals(".")) {
            try {
                // Intenta convertir el comando a un nÃºmero.
                int numeroFila = Integer.parseInt(comando);
                if (rs.absolute(numeroFila)) {
                    mostrarFilaActual(rs);
                } else {
                    System.out.println("Error: La fila " + numeroFila + " no existe.");
                }
            } catch (NumberFormatException e) {
                // Si no es un nÃºmero, procesa los comandos de texto.
                switch (comando.toLowerCase()) {
                    case "k":
                        if (rs.next()) {
                            mostrarFilaActual(rs);
                        } else {
                            // Si rs.next() devuelve false, ya estÃ¡bamos en la Ãºltima fila.
                            // Para evitar mover el cursor mÃ¡s allÃ¡ del final, lo devolvemos a la Ãºltima.
                            rs.last();
                            System.out.println("EstÃ¡s en la Ãºltima fila.");
                        }
                        break;
                    case "d":
                        if (rs.previous()) {
                            mostrarFilaActual(rs);
                        } else {
                            // Similar al caso 'k', devolvemos el cursor a la primera posiciÃ³n.
                            rs.first();
                            System.out.println("EstÃ¡s en la primera fila.");
                        }
                        break;
                    default:
                        System.out.println("Comando no reconocido. Usa 'k', 'd', un nÃºmero o '.' para salir.");
                        break;
                }
            }
        }
    }

    /**
     * Muestra el contenido de la fila actual del ResultSet.
     * Obtiene los metadatos para mostrar los nombres de las columnas.
     * @param rs El ResultSet posicionado en la fila a mostrar.
     * @throws SQLException Si hay un error al acceder a los datos.
     */
    private static void mostrarFilaActual(ResultSet rs) throws SQLException {
        // --- Parte importante: ObtenciÃ³n y muestra de datos ---
        // ResultSetMetaData nos permite obtener informaciÃ³n sobre las columnas
        // del resultado de la consulta, como el nombre y el nÃºmero de columnas.
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumnas = rsmd.getColumnCount();

        System.out.println("\n--- Fila " + rs.getRow() + " ---");

        for (int i = 1; i <= numColumnas; i++) {
            String nombreColumna = rsmd.getColumnName(i);
            String valorColumna = rs.getString(i);
            System.out.printf("  %-20s: %s%n", nombreColumna, valorColumna);
        }
    }
}

