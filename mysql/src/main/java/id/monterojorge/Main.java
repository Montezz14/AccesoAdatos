package id.monterojorge;

import id.monterojorge.dao.DatabaseConnection;
import id.monterojorge.dao.Dao;
import id.monterojorge.pojos.Cliente;
import id.monterojorge.pojos.ClienteNuevo;
import id.monterojorge.pojos.LineaFactura;
import id.monterojorge.pojos.ResultadoListado;
import id.monterojorge.print.ImprimirResultados;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Main {

    public static String CATALOGO = "hr_database";
    public static String NOMBRE_TABLA = "CLIENTES";
    public static String T_FACTURAS = "FACTURAS";
    public static String T_LINEAS_FACTURA = "LINEAS_FACTURA";
    public static String INSERT_CLIENTES = "INSERT INTO CLIENTES(DNI,APELLIDOS,CP) VALUES "
            + "('78901234X','NADALES','44126'),"
            + "('89012345E','HOJAS', null),"
            + "('56789012B','SAMPER','29730'),"
            + "('09876543K','LAMIQUIZ', null);";


    public static void main(String[] args) {
        // Ejemplo de uso
        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                throw new Exception("Error al obtener la conexiÃ³n a la base de datos.");
            }

            System.out.println("Hemos obtenido la conexiÃ³n a la base de datos");
            Dao dao = new Dao(connection);
            ImprimirResultados print = new ImprimirResultados();
            /**
             * Ejecutamos una sentencia DDL para crear una tabla
             */
            // Asegurarnos de que la tabla exista antes de consultar/insertar
            dao.crearTablaClientesSiNoExiste();
            // Asegurar que la tabla CLIENTES tenga exactamente los registros deseados
            dao.enforceTargetClientesState();
            // Imprimimos los resultados
            //print.imprimirTablas(connection, CATALOGO);

            // Insertamos registros en la tabla clientes
            //dao.insertarDatosConStatement(connection, INSERT_CLIENTES);
            // Sacamos por consola los registros insertados
            //print.imprimirRegistros(connection, CATALOGO, NOMBRE_TABLA);

            // --- Datos de los 5 nuevos clientes a insertar ---
        
            List<Cliente> nuevosClientes = Arrays.asList(
                    new Cliente("12345678A", "PÃ©rez GÃ³mez", 28001),
                    new Cliente("23456789B", "LÃ³pez MartÃ­n", 41002),
                    new Cliente("34567890C", "SÃ¡nchez Ruiz", 46003),
                    new Cliente("45678901D", "FernÃ¡ndez DÃ­az", 98004),
                    new Cliente("56789012E", "Moreno JimÃ©nez", 50005)
            );

//            dao.insertarClientes(connection, nuevosClientes);
//            print.imprimirRegistros(connection, CATALOGO, NOMBRE_TABLA);
            List<Cliente> nuevosClientes2 = Arrays.asList(
                    new Cliente("15345678A", "Ana GÃ³mez", 28001),
                    new Cliente("26456789B", "Jose MartÃ­n", 41002),
                    new Cliente("37567890C", "Ramon Ruiz", 46003),
                    new Cliente("48678901D", "Lucia DÃ­az", 98004),
                    new Cliente("59789012E", "Amalia JimÃ©nez", 50005)
            );
//            dao.insertarClientesBatchConTransaccion(connection, nuevosClientes2);
//            print.imprimirRegistros(connection, CATALOGO, NOMBRE_TABLA);

            // Preparamos los datos para las nuevas facturas
            List<String> dnis = Arrays.asList(
                    "78901234X",
                    "09876543K",
                    "15345678A",
                    "INVALIDO", // DNI que podrÃ­a causar un error para probar el rollback
                    "59789012E"
            );

            List<LineaFactura> lineas = Arrays.asList(
                    new LineaFactura("TORNILLOS", 10),
                    new LineaFactura("TUERCAS", 50),
                    new LineaFactura("ARANDELAS", 100),
                    new LineaFactura("TACOS", 150)
            );

            // Llamamos a nuestro mÃ©todo para procesar el lote de facturas
//            Map<String, Integer> resultados = dao.crearFacturas(connection, dnis, lineas);
//
//            System.out.println("\n--- RESUMEN DEL PROCESO ---");
//            System.out.println("Facturas creadas exitosamente: " + resultados.size() + " de " + dnis.size());
//            resultados.forEach((dni, numFactura) ->
//                    System.out.println("  - DNI: " + dni + " -> Factura NÂº: " + numFactura)
//            );
//            print.imprimirRegistros(connection, CATALOGO, T_FACTURAS);
//            print.imprimirRegistros(connection, CATALOGO, T_LINEAS_FACTURA);

            // La lÃ³gica de negocio ahora es una simple llamada a un mÃ©todo.
//            String dniBusqueda = "78901234X";
//            ResultadoListado resultado = dao.llamarListadoClientes(connection, dniBusqueda);
//
//            // La responsabilidad de mostrar los datos se queda en el main.
//            System.out.println("=> Valor del parÃ¡metro INOUT devuelto: " + resultado.getContadorInOut());
//            System.out.println("Clientes encontrados:");
//
//            int nCli = 0;
//            for (Cliente cliente : resultado.getClientes()) {
//                System.out.println(" [" + (++nCli) + "] " + cliente.toString());
//            }

//            dao.obtenerYMostrarApellidosAlternativo("78901234X", connection);

//// == INICIO DE LA TRANSACCIÃ“N ==
//            // La responsabilidad de la transacciÃ³n se queda en el mÃ©todo principal.
//            connection.setAutoCommit(false);
//
//            // Preparamos los datos para la operaciÃ³n
//            String nuevoCp = "02568";
//            ClienteNuevo nuevoCliente = new ClienteNuevo("24862486S", "ZURITA", "33983");
//
//            System.out.println("Iniciando operaciÃ³n de modificaciÃ³n de clientes...");
//            // Llamamos a nuestro mÃ©todo de lÃ³gica de negocio
//            dao.modificarClientesConResultSet(connection, nuevoCp, nuevoCliente);
//
//            // Si el mÃ©todo termina sin lanzar una excepciÃ³n, confirmamos la transacciÃ³n.
//            connection.commit();
//            System.out.println("\nTransacciÃ³n confirmada (COMMIT) con Ã©xito.");
//
//            print.imprimirRegistros(connection, CATALOGO, NOMBRE_TABLA);

        // Los datos ahora son una lista de objetos, mucho mÃ¡s legible y segura.
            List<Cliente> clientesNuevos = Arrays.asList(
                    new Cliente("13579135G", "Maria Torres", 32564),
                    new Cliente("24680246G", "Pedro Marin", 25865),
                    new Cliente("96307418R", "Blanca Fernandez", 19273)
            );
            // == INICIO DE LA TRANSACCIÃ“N ==
            // La gestiÃ³n de la transacciÃ³n (commit/rollback) se queda en el mÃ©todo 'main'.
            connection.setAutoCommit(false);

            try {
                // Llamamos a nuestro mÃ©todo reutilizable.
                int[] resultados = dao.insertarClientesEnLote(connection, clientesNuevos);

                // == FIN DE LA TRANSACCIÃ“N (Ã‰XITO) ==
                connection.commit();

                System.out.println("TransacciÃ³n confirmada (COMMIT) con Ã©xito.");
                System.out.println("Resultados del lote: " + Arrays.toString(resultados));
                // Un resultado de 1 (o Statement.SUCCESS_NO_INFO) por cada inserciÃ³n indica Ã©xito.
                Arrays.stream(resultados).sequential().forEach(r -> System.out.println("Resultado: " + r));

            } catch (SQLException e) {
                System.err.println("Error de SQL, se desharÃ¡n los cambios (ROLLBACK).");
                e.printStackTrace(System.err);
                // Si algo falla, hacemos rollback
                connection.rollback();
                System.err.println("Rollback realizado.");
            }
            connection.setAutoCommit(true);

            print.imprimirRegistros(connection, CATALOGO, NOMBRE_TABLA);

            dao.insertarDatosConStatement(connection, INSERT_CLIENTES);

            // ====== ACTIVIDAD 4.3: Mostrar empleados en orden inverso ======
            // Descomenta las siguientes lÃ­neas para ejecutar la actividad 4.3
            
            // MÃ©todo 1: Usando la consulta SQL original exacta y invirtiendo en Java
            // dao.mostrarEmpleadosOrdenInverso();
            
            // MÃ©todo 2: Alternativo mÃ¡s eficiente (con ORDER BY DESC)
            // dao.mostrarEmpleadosOrdenInversoAlternativo();

            // ====== ACTIVIDAD 4.4: Contar filas sin recorrer ResultSet ======
            // Descomenta las siguientes lÃ­neas para ejecutar la actividad 4.4
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ACTIVIDAD 4.4: CONTAR FILAS SIN RECORRER RESULTSET");
            System.out.println("=".repeat(60));
            
            // MÃ©todo 1: ResultSet Scrollable con last() y getRow()
            dao.contarFilasConScrollableResultSet();
            
            // MÃ©todo 2: Consulta COUNT separada (mÃ¡s eficiente)
            dao.contarFilasConConsultaCount();
            
            // MÃ©todo 3: Comparativo de rendimiento
            dao.compararMetodosConteo();
            
            // MÃ©todo 4: ExplicaciÃ³n detallada de las tÃ©cnicas
            dao.explicarTecnicasConteoFilas();

            System.out.println("\n" + "=".repeat(60));
            System.out.println("ACTIVIDAD 4.4 COMPLETADA EXITOSAMENTE");
            System.out.println("=".repeat(60));

            // ====== ACTIVIDAD 4.5: Consultar clientes con PreparedStatement ======
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ACTIVIDAD 4.5: CONSULTA INDIVIDUAL CON PREPARED STATEMENT");
            System.out.println("=".repeat(60));

            // Ejemplo 1: Consultar clientes especÃ­ficos por DNI
            String[] dnisEspecificos = {
                "78901234X",    // Cliente existente
                "89012345E",    // Cliente existente  
                "99999999Z",    // Cliente inexistente (para mostrar manejo de errores)
                "56789012B"     // Cliente existente
            };
            
            System.out.println("Ejemplo 1: Consultando clientes especÃ­ficos");
            dao.consultarClientesPorDNI(dnisEspecificos);

            // Ejemplo 2: Consultar todos los clientes individualmente
            System.out.println("Ejemplo 2: Consultando TODOS los clientes individualmente");
            dao.consultarTodosLosClientesIndividualmente();

            // Ejemplo 3: DemostraciÃ³n de buenas prÃ¡cticas
            System.out.println("Ejemplo 3: DemostraciÃ³n de buenas prÃ¡cticas");
            dao.demostrarVentajasPreparedStatement();

            System.out.println("ACTIVIDAD 4.5 COMPLETADA EXITOSAMENTE");

            // ====== ACTIVIDAD 4.6: Tabla COMPANIES y operaciones batch ======
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ACTIVIDAD 4.6: GESTIÃ“N DE COMPAÃ‘ÃAS CON BATCH");
            System.out.println("=".repeat(60));

            // Paso 1: Crear la tabla COMPANIES
            dao.crearTablaCompanies();

            // Paso 2: Preparar datos de ejemplo para insertar
            List<id.monterojorge.pojos.Company> companiesEjemplo = Arrays.asList(
                // CompaÃ±Ã­as vÃ¡lidas
                new id.monterojorge.pojos.Company("12345678A", "TecnologÃ­a Innovadora S.L.", "TecnologÃ­a"),
                new id.monterojorge.pojos.Company("87654321B", "ConsultorÃ­a Empresarial S.A.", "ConsultorÃ­a"),
                new id.monterojorge.pojos.Company("11223344C", "Distribuciones del Norte", "DistribuciÃ³n"),
                new id.monterojorge.pojos.Company("55667788D", "Manufacturas Especializadas", "Manufactura"),
                new id.monterojorge.pojos.Company("99887766E", "Servicios Financieros Plus", "Finanzas"),
                
                // CompaÃ±Ã­as con errores para probar validaciÃ³n
                new id.monterojorge.pojos.Company("INVALID01", "CIF InvÃ¡lido S.L.", "TecnologÃ­a"), // CIF invÃ¡lido
                new id.monterojorge.pojos.Company("12345678F", "", "TecnologÃ­a"), // Nombre vacÃ­o
                new id.monterojorge.pojos.Company("12345678G", "Sin Sector S.L.", ""), // Sector vacÃ­o
                null // CompaÃ±Ã­a null
            );

            System.out.println("Insertando compaÃ±Ã­as de ejemplo (incluye casos de error para demostraciÃ³n)");
            
            // Paso 3: Insertar compaÃ±Ã­as usando batch con transacciÃ³n controlada
            int companiesInsertadas = dao.insertarCompaniesEnBatch(companiesEjemplo);
            
            System.out.println("Proceso completado. CompaÃ±Ã­as insertadas: " + companiesInsertadas);

            // Paso 4: Mostrar las compaÃ±Ã­as insertadas
            dao.mostrarCompaniesInsertadas();

            // Paso 5: DemostraciÃ³n de escenarios avanzados
            System.out.println("Demostrando escenarios avanzados de batch insert:");
            dao.demostrarEscenariosCompanies();

            System.out.println("ACTIVIDAD 4.6 COMPLETADA EXITOSAMENTE");

            // ====== ACTIVIDAD 4.7: FunciÃ³n almacenada en MySQL ======
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ACTIVIDAD 4.7: FUNCIÃ“N ALMACENADA MYSQL");
            System.out.println("=".repeat(60));

            // Paso 1: Crear la funciÃ³n almacenada en MySQL
            System.out.println("Paso 1: Creando funciÃ³n almacenada 'obtener_apellidos_cliente'");
            dao.crearFuncionAlmacenadaObtenerApellidos();

            // Paso 2: Probar la funciÃ³n con DNIs conocidos
            String[] dnisParaProbar = {
                "78901234X",    // Cliente existente (NADALES)
                "89012345E",    // Cliente existente (ROJAS)
                "56789012B",    // Cliente existente (SAMPER)
                "99999999Z",    // Cliente inexistente
                "INVALID00",    // DNI invÃ¡lido
                null            // DNI null (para probar validaciÃ³n)
            };

            System.out.println("Paso 2: Probando funciÃ³n almacenada con mÃºltiples DNIs");
            dao.demostrarFuncionAlmacenada(dnisParaProbar);

            // Paso 3: Comparar rendimiento funciÃ³n vs consulta directa
            System.out.println("Paso 3: Comparando rendimiento");
            dao.compararRendimientoFuncionVsConsulta("78901234X");

            // Paso 4: Ejemplos individuales de uso
            System.out.println("Paso 4: Ejemplos de uso individual");
            System.out.println("â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•â•");
            
            String[] ejemplosIndividuales = {"78901234X", "89012345E", "99999999Z"};
            for (String dniEjemplo : ejemplosIndividuales) {
                try {
                    System.out.println("\nConsultando DNI: " + dniEjemplo);
                    String apellidos = dao.llamarFuncionObtenerApellidos(dniEjemplo);
                    
                    if (apellidos != null) {
                        System.out.println("   Apellidos obtenidos: " + apellidos);
                    } else {
                        System.out.println("   Cliente no encontrado");
                    }
                } catch (Exception e) {
                    System.out.println("   Error: " + e.getMessage());
                }
            }

            // Paso 5: Demostrar tÃ©cnicas avanzadas (opcional)
            System.out.println("\nPaso 5: TÃ©cnicas avanzadas con funciones almacenadas");
            dao.demostrarTecnicasAvanzadasFunciones();

            System.out.println("\nACTIVIDAD 4.7 COMPLETADA EXITOSAMENTE");
            System.out.println("\nRESUMEN TÃ‰CNICO:");
            System.out.println("   FunciÃ³n MySQL creada: obtener_apellidos_cliente(dni VARCHAR(20))");
            System.out.println("   Retorna: VARCHAR(255) con los apellidos del cliente");
            System.out.println("   Llamada desde Java: SELECT obtener_apellidos_cliente(?)");
            System.out.println("   Manejo completo de casos de error y validaciÃ³n");
            System.out.println("   Funciones adicionales: validar_dni_formato, obtener_info_cliente");

            // ====== ACTIVIDAD 4.8: Navegador interactivo de tabla ======
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ACTIVIDAD 4.8: NAVEGADOR INTERACTIVO DE TABLA");
            System.out.println("=".repeat(60));
            
            // NavegaciÃ³n interactiva de la tabla CLIENTES
            // Descomenta la siguiente lÃ­nea para ejecutar el navegador
            dao.navegadorInteractivoTabla("CLIENTES");

            System.out.println("\nACTIVIDAD 4.8 COMPLETADA EXITOSAMENTE");

            // Cerramos la conexion
            connection.close();
        } catch (Exception e) {
            System.err.println("Fallo al intentar obtener la conexion a la base de datos.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
