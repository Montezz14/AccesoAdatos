# HIBERNATE_PROJECT — Tema 5 (Acceso a Datos)

Proyecto de persistencia de objetos con **Hibernate** y **Spring Boot** que resuelve las
actividades 5.1 a 5.7. La base de datos es `proyecto_orm` sobre **MySQL**.

## Estructura del proyecto

```
hibernate_project/
├── pom.xml
├── sql/
│   ├── 01_crear_bd_proyecto_orm.sql   (Actividad 5.1)
│   ├── 02_indices_unicos.sql          (Actividad 5.6)
│   └── 03_alter_proyecto_unique.sql   (Actividad 5.7)
└── src/main/
    ├── java/com/accdatos/tema5/
    │   ├── AccesoADatosTema5Application.java   (clase main)
    │   ├── pojos/          → entidades JPA (@Entity)
    │   │   ├── Sede.java
    │   │   ├── Departamento.java
    │   │   ├── Empleado.java
    │   │   ├── Proyecto.java
    │   │   └── EmpleadoDatosProf.java
    │   ├── repositorios/   → interfaces JpaRepository
    │   │   ├── SedeRepository.java
    │   │   ├── DepartamentoRepository.java
    │   │   ├── EmpleadoRepository.java
    │   │   ├── ProyectoRepository.java
    │   │   └── EmpleadoDatosProfRepository.java
    │   ├── servicios/      → logica de negocio de cada actividad
    │   │   ├── SetupInicialService.java   (5.4)
    │   │   ├── ServiceActividad5.java     (5.5)
    │   │   ├── ServiceActividad6.java     (5.6)
    │   │   ├── ServiceActividad7.java     (5.7)
    │   │   └── AppRunner.java             (runner principal)
    │   └── util/
    │       └── VerificadorConexion.java   (5.3 — verifica la conexion)
    └── resources/
        └── application.properties         (conexion a MySQL)
```

## Correspondencia actividad → archivo

| Actividad | Descripcion                                                        | Archivo principal               |
|-----------|--------------------------------------------------------------------|---------------------------------|
| 5.1       | Crear la BD `proyecto_orm` y sus tablas en MySQL                   | `sql/01_crear_bd_proyecto_orm.sql` |
| 5.2       | Crear el proyecto Hibernate con Spring Boot en IntelliJ           | `pom.xml` + estructura del proyecto |
| 5.3       | Verificar la conexion a la BD con Spring Boot                     | `util/VerificadorConexion.java` |
| 5.4       | Crear una sede, un departamento y un empleado                     | `servicios/SetupInicialService.java` |
| 5.5       | Una sede, dos departamentos y dos empleados por departamento      | `servicios/ServiceActividad5.java` |
| 5.6       | Gestionar la excepcion de nombre duplicado (indices unicos)       | `servicios/ServiceActividad6.java` + `sql/02_indices_unicos.sql` |
| 5.7       | Crear proyectos y datos profesionales de empleados                | `servicios/ServiceActividad7.java` + `sql/03_alter_proyecto_unique.sql` |

## Como ejecutar

1. Arrancar MySQL y ejecutar `sql/01_crear_bd_proyecto_orm.sql`.
2. Ajustar usuario/contraseña en `src/main/resources/application.properties`.
3. En `servicios/AppRunner.java`, descomentar el bloque de la actividad que se quiera ejecutar.
4. Ejecutar la aplicacion (`mvn spring-boot:run` o desde IntelliJ).
5. Para las actividades 5.6 y 5.7, ejecutar antes los scripts SQL de restricciones correspondientes.

> Nota: `VerificadorConexion` (5.3) y `AppRunner` (5.5+) implementan ambos `CommandLineRunner`.
> Para la 5.3 conviene dejar activo solo `VerificadorConexion`; para el resto, comentar su
> anotacion `@Component` y usar `AppRunner`.
