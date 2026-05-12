# ProyectoHrSpringBoot - Tema 6: Acceso a Datos

## Descripción
Este es un proyecto completo de Spring Boot que implementa un sistema de gestión de recursos humanos (HR) siguiendo todos los pasos del Tema 6: Acceso a Datos.

## Requisitos Previos
- **Java 17+** instalado
- **MySQL Server 8** en ejecución
- **Maven 3.6+** instalado (opcional, ya que el proyecto tiene Maven Wrapper)
- **Un IDE** como IntelliJ IDEA o VS Code

## Configuración de Base de Datos

### 1. Crear la base de datos
Ejecuta esto en tu cliente MySQL:

```sql
CREATE DATABASE hr_spring;
USE hr_spring;
```

### 2. Poblar con datos (Opcional)
Si tienes un script SQL con los datos del esquema HR, ejecútalo. Si no, el primer arranque creará las tablas vacías.

## Instalación y Ejecución

### Opción 1: Desde IntelliJ IDEA
1. Abre el proyecto en IntelliJ
2. Espera a que Maven descargue las dependencias
3. Haz clic en el triángulo verde (Run) junto a la clase `ProyectoHrSpringBootApplication`
4. Espera a que veas el mensaje: `Tomcat started on port(s): 8080 (http)`

### Opción 2: Desde Línea de Comandos
```bash
# Windows
.\mvnw spring-boot:run

# Mac/Linux
./mvnw spring-boot:run
```

## Acceso a la Aplicación

Una vez iniciada:

1. **Abre tu navegador** en: `http://localhost:8080`
2. **Login:**
   - Usuario: `admin`
   - Contraseña: `admin`

## Estructura del Proyecto

```
ProyectoHrSpringBoot/
├── src/main/java/com/curso/proyectohr/
│   ├── dominio/           # Entidades JPA (@Entity)
│   │   ├── Region.java
│   │   ├── Country.java
│   │   ├── Location.java
│   │   ├── Job.java
│   │   ├── Department.java
│   │   ├── Employee.java
│   │   ├── JobHistory.java
│   │   └── Usuario.java
│   ├── repositorios/      # Interfaces JpaRepository
│   │   ├── RegionRepository.java
│   │   ├── EmployeeRepository.java
│   │   └── ... (otras)
│   ├── servicios/         # Lógica de negocio (@Service)
│   │   ├── EmployeeService.java
│   │   └── RegionService.java
│   ├── controller/        # Controladores REST (@RestController)
│   │   ├── EmployeeController.java
│   │   └── RegionController.java
│   ├── config/           # Configuración
│   │   ├── SecurityConfig.java
│   │   └── CustomUserDetailsService.java
│   ├── excepciones/      # Manejo de excepciones
│   │   ├── RecursoNoEncontradoException.java
│   │   └── GlobalExceptionHandler.java
│   └── util/             # Utilidades
│       └── DataInitializer.java
├── src/main/resources/
│   ├── static/           # HTML, CSS, JavaScript
│   │   ├── index.html
│   │   ├── employees.html
│   │   ├── styles.css
│   │   └── app.js
│   └── application.properties  # Configuración
└── pom.xml               # Dependencias Maven
```

## Funcionamiento

### Ejercicio 6.2 - Inicialización del Proyecto
✅ Completado: Proyecto generado con Spring Initializr, estructura base lista.

### Ejercicio 6.3 - Entidades JPA
✅ Completado: Todas las entidades están mapeadas correctamente con relaciones Many-to-One, One-to-Many, y claves primarias compuestas.

### Ejercicio 6.4 - Lombok y Generación de Schema
✅ Completado: Se usa Lombok con @Data, @NoArgsConstructor, @AllArgsConstructor. Hibernate genera el schema automáticamente con `ddl-auto=update`.

### Ejercicio 6.5 - Repositorios
✅ Completado: Interfaces que extienden JpaRepository con Query Methods personalizados.

### Ejercicio 6.6 - Servicios
✅ Completado: Clases de lógica de negocio con @Transactional, CRUD completo, y métodos de consulta.

### Ejercicio 6.7 - Controladores REST
✅ Completado: @RestController con endpoints GET, POST, PUT, DELETE, incluyendo búsquedas personalizadas.

### Ejercicio 6.8 - Frontend HTML/JavaScript
✅ Completado: Interfaz web con formulario CRUD y tabla dinámica. Fetch API para comunicarse con la API REST.

### Ejercicio 6.9 - Logging y Excepciones
✅ Completado: GlobalExceptionHandler para manejar errores, excepciones personalizadas.

### Ejercicio 6.10 - Spring Security
✅ Completado: Autenticación con BD, contraseñas cifradas con BCrypt, usuario admin creado automáticamente.

## Endpoints de la API

### Empleados
- `GET /api/employees` - Obtener todos
- `GET /api/employees/{id}` - Obtener por ID
- `POST /api/employees` - Crear nuevo
- `PUT /api/employees/{id}` - Actualizar
- `DELETE /api/employees/{id}` - Eliminar
- `GET /api/employees/search/high-salary?salary=50000` - Buscar por salario
- `GET /api/employees/search/by-name?apellido=King` - Buscar por apellido

### Regiones
- `GET /api/regions` - Obtener todas
- `GET /api/regions/{id}` - Obtener por ID
- `POST /api/regions` - Crear nueva
- `DELETE /api/regions/{id}` - Eliminar

## Prueba con Postman

Si quieres probar los endpoints con Postman:

1. **Descarga Postman** desde https://www.postman.com/downloads/
2. **Configura la autenticación Basic Auth:**
   - Usuario: `admin`
   - Contraseña: `admin`
3. **Prueba un GET** a `http://localhost:8080/api/employees`

## Solución de Problemas

### Error: "Access denied for user 'root'@'localhost'"
- Verifica que MySQL está corriendo
- Comprueba user/password en `application.properties`

### Error: "Port 8080 is already in use"
- El servidor sigue corriendo. Termínalo y reinicia.

### Error: "Table doesn't exist"
- Espera a que Hibernate cree las tablas (primera ejecución)
- O ejecuta manualmente: `CREATE TABLE app_users (...)`

## Tecnologías Utilizadas

- **Spring Boot 3.3.0** - Framework principal
- **Spring Data JPA** - Acceso a datos ORM
- **Hibernate** - Implementación JPA
- **Spring Security** - Autenticación y autorización
- **MySQL 8** - Base de datos
- **Lombok** - Reducir código repetitivo
- **Maven** - Gestor de dependencias
- **HTML5 / CSS3 / JavaScript ES6** - Frontend

## Detalles de Implementación

### Autenticación
- Usuarios almacenados en tabla `app_users`
- Contraseñas cifradas con BCrypt
- Sesiones por cookies

### Transacciones
- Todas las operaciones de escritura están envueltas en `@Transactional`
- Rollback automático en caso de error

### Validación
- Validación básica en servicios
- Manejo global de excepciones
- Respuestas JSON con códigos HTTP apropiados

### Entidades Complejas
- **Employee** tiene relaciones recursivas (manager) y múltiples ManyToOne
- **JobHistory** tiene clave primaria compuesta (employee_id + start_date)
- Exclusiones en toString/equals/hashCode para evitar bucles infinitos

## Próximos Pasos

Para mejorar el proyecto:
1. Añadir validación con `@Valid` y `@Validated`
2. Implementar paginación en las listas
3. Añadir más endpoints para otras entidades
4. Mejorar el frontend con un framework como React o Vue
5. Configurar logging persistente en archivos

## Autor
Proyecto educativo para aprender Spring Boot y JPA.

## Licencia
Este proyecto es de código abierto y está disponible para propósitos educativos.
