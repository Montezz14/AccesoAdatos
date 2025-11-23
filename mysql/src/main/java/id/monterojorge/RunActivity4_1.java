package id.monterojorge;

import java.sql.Connection;

import id.monterojorge.dao.Dao;
import id.monterojorge.dao.DatabaseConnection;

public class RunActivity4_1 {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.err.println("No se obtuvo conexión; abortando.");
                return;
            }

            Dao dao = new Dao(conn);
            System.out.println("[4.1] Invocando crearTablaClientesSiNoExiste()...");
            dao.crearTablaClientesSiNoExiste();
            System.out.println("[4.1] crearTablaClientesSiNoExiste() completado.");

            System.out.println("[4.1] Invocando enforceTargetClientesState() para asegurar registros...");
            dao.enforceTargetClientesState();
            System.out.println("[4.1] enforceTargetClientesState() completado.");

        } catch (Exception e) {
            System.err.println("[4.1] Error ejecutando actividad 4.1: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
