# Enunciados Tema 6 - Acceso a Datos

## ACTIVIDAD 6.2 - Inicialización del Proyecto (Spring Initializr)
**Estado: ✅ COMPLETADO**

- [x] Crear proyecto con Spring Initializr
- [x] Configurar dependencias (Web, JPA, MySQL Driver, Security, Lombok, Test)
- [x] Importar en IDE
- [x] Crear índice HTML de bienvenida
- [x] Ejecutar y validar que funciona

**Archivos resultantes:**
- `pom.xml` - Configuración Maven con todas las dependencias
- `application.properties` - Propiedades de la aplicación
- `ProyectoHrSpringBootApplication.java` - Clase principal
- `index.html` - Página de inicio

---

## ACTIVIDAD 6.3 - Configuración DataSource y JPA/Entidades
**Estado: ✅ COMPLETADO**

- [x] Configurar conexión MySQL en `application.properties`
- [x] Crear entidades JPA mapeadas al esquema HR
- [x] Mapear relaciones @ManyToOne, @OneToMany
- [x] Crear DatabaseMetadataPrinter para verificar la BD

**Entidades creadas:**
- `Region.java` - Mapeo tabla regions
- `Country.java` - Mapeo tabla countries (relación N:1 con Region)
- `Location.java` - Mapeo tabla locations (relación N:1 con Country)
- `Job.java` - Mapeo tabla jobs
- `Department.java` - Mapeo tabla departments (relación N:1 con Location)
- `Employee.java` - Mapeo tabla employees (múltiples relaciones)
- `JobHistory.java` - Mapeo tabla job_history (clave primaria compuesta)
- `JobHistoryId.java` - Clase para PK compuesta

---

## ACTIVIDAD 6.4 - Refactorización con Lombok y Generación de Schema
**Estado: ✅ COMPLETADO**

- [x] Usar anotaciones Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor)
- [x] Usar @ToString.Exclude y @EqualsAndHashCode.Exclude en relaciones
- [x] Configurar Hibernate para generar schema automáticamente (`ddl-auto=update`)
- [x] Probar generación automática de tablas

**Beneficios de Lombok:**
- Reduce código boilerplate (getters, setters, constructores)
- Evita errores comunes en equals/hashCode
- Código más limpio y legible

---

## ACTIVIDAD 6.5 - Capa de Repositorios (Spring Data JPA)
**Estado: ✅ COMPLETADO**

- [x] Crear interfaces que extienden JpaRepository
- [x] Implementar Query Methods personalizados
- [x] Crear repositorio para entidad con PK compuesta
- [x] Inyectar repositorios en componentes de prueba

**Repositorios creados:**
- `RegionRepository` - CRUD para regions
- `CountryRepository` - CRUD para countries
- `LocationRepository` - CRUD para locations
- `JobRepository` - CRUD para jobs
- `DepartmentRepository` - CRUD para departments
- `EmployeeRepository` - CRUD + Query Methods (findBySalaryGreaterThan, etc.)
- `JobHistoryRepository` - CRUD con PK compuesta
- `UsuarioRepository` - Para autenticación

---

## ACTIVIDAD 6.6 - Capa de Servicios (Business Logic)
**Estado: ✅ COMPLETADO**

- [x] Crear clases @Service para lógica de negocio
- [x] Implementar CRUD completo con @Transactional
- [x] Separar consultas de negocio
- [x] Usar inyección de dependencias

**Servicios creados:**
- `EmployeeService` - CRUD de empleados + búsquedas especializadas
- `RegionService` - CRUD de regiones

**Métodos transaccionales:**
- `@Transactional` para escritura (INSERT, UPDATE, DELETE)
- `@Transactional(readOnly=true)` para consultas (optimiza)

---

## ACTIVIDAD 6.7 - Capa de API REST (Controladores)
**Estado: ✅ COMPLETADO**

- [x] Crear @RestController para exponer endpoints
- [x] Mapear operaciones CRUD a métodos HTTP
- [x] Usar ResponseEntity para controlar códigos HTTP
- [x] Implementar búsquedas personalizadas
- [x] Configurar SecurityConfig para permitir peticiones POST/PUT/DELETE

**Controladores creados:**
- `EmployeeController` - API completa de employees
  - GET /api/employees - listar todos
  - GET /api/employees/{id} - obtener uno
  - POST /api/employees - crear
  - PUT /api/employees/{id} - actualizar
  - DELETE /api/employees/{id} - eliminar
  - GET /api/employees/search/high-salary - búsqueda
  - GET /api/employees/search/by-name - búsqueda

- `RegionController` - API de regions

---

## ACTIVIDAD 6.8 - Frontend HTML/JavaScript
**Estado: ✅ COMPLETADO**

- [x] Crear página estática con HTML5
- [x] Implementar formulario CRUD
- [x] Tabla dinámica con datos de la API
- [x] Funciones JavaScript para CRUD
- [x] Usar Fetch API para comunicación
- [x] Diseño responsivo con CSS3

**Archivos frontend:**
- `index.html` - Dashboard principal
- `employees.html` - Gestión de empleados
- `styles.css` - Estilos CSS (responsive)
- `app.js` - Lógica JavaScript con Fetch API

**Funcionalidades:**
- Cargar y mostrar empleados en tabla
- Crear nuevo empleado con formulario
- Editar empleado (cargar datos, actualizar)
- Eliminar empleado con confirmación
- Mensajes de éxito/error flotantes
- Búsqueda básica por apellido y salario

---

## ACTIVIDAD 6.9 - Logging y Gestión Global de Excepciones
**Estado: ✅ COMPLETADO**

- [x] Implementar excepciones personalizadas
- [x] Crear @ControllerAdvice para manejo global
- [x] Usar SLF4J para logging (comentarios en servicios)
- [x] Respuestas JSON estructuradas para errores

**Componentes:**
- `RecursoNoEncontradoException` - Excepción custom
- `GlobalExceptionHandler` - Atrapa y formatea excepciones
  - Maneja 404 Not Found
  - Maneja 500 Internal Server Error
  - Devuelve JSON con timestamp, status, message

---

## ACTIVIDAD 6.10 - Implementación de Spring Security
**Estado: ✅ COMPLETADO**

- [x] Crear entidad Usuario
- [x] Configurar PasswordEncoder (BCrypt)
- [x] Implementar UserDetailsService
- [x] Crear SecurityFilterChain
- [x] Crear usuario admin automáticamente

**Seguridad implementada:**
- Autenticación con BD (tabla app_users)
- Contraseñas cifradas con BCrypt
- SessionCookies para mantener sesión
- Basic Auth para Postman
- Autorización: algunas URLs públicas, API requiere login
- DataInitializer crea usuario admin:admin

---

## Resumen de Tecnologías Utilizadas

### Backend
- **Spring Boot 3.3.0**
- **Spring Data JPA** - ORM
- **Hibernate** - Implementación JPA
- **Spring Security** - Autenticación
- **MySQL 8** - Base de datos
- **Lombok** - Reducir código
- **Maven** - Build tool

### Frontend
- **HTML5**
- **CSS3** (Responsive design)
- **JavaScript ES6**
- **Fetch API** (HTTP requests)

### Patrón de Arquitectura
```
Cliente HTTP (Navegador/Postman)
         ↓
  Spring Security (Autenticación)
         ↓
  @RestController (Endpoints)
         ↓
  @Service (Lógica de Negocio)
         ↓
  @Repository (JpaRepository)
         ↓
  Hibernate/JPA (ORM)
         ↓
  MySQL Database
```

---

## Cómo Ejecutar

```bash
# Crear base de datos
mysql> CREATE DATABASE hr_spring;

# Ejecutar proyecto
./mvnw spring-boot:run

# Abrir navegador
http://localhost:8080

# Login
usuario: admin
contraseña: admin
```

---

## Notas Importantes

1. **Primera ejecución:** Hibernate creará las tablas automáticamente
2. **ddl-auto=update:** Crea/modifica tablas según las entidades
3. **Contraseñas:** Nunca se almacenan en texto plano, siempre cifradas
4. **Transacciones:** Spring revierte cambios si algo falla
5. **Query Methods:** Spring genera SQL automáticamente según el nombre
6. **Lombok:** Reduce código sin cambiar la funcionalidad

---

Proyecto completado siguiendo los 9 ejercicios del Tema 6: Acceso a Datos 🎉
