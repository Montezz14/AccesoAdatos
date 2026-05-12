package com.curso.proyectohr.controller;

import com.curso.proyectohr.dominio.Employee;
import com.curso.proyectohr.servicios.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

// @RestController indica que esta clase maneja peticiones HTTP
// Los métodos devuelven directamente JSON, no vistas HTML
// @RequestMapping define la ruta base para todos los endpoints
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET /api/employees
    // Obtiene todos los empleados
    @GetMapping
    public ResponseEntity<List<Employee>> obtenerTodos() {
        List<Employee> empleados = employeeService.obtenerTodos();
        return ResponseEntity.ok(empleados);
    }

    // GET /api/employees/{id}
    // Obtiene un empleado por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> obtenerPorId(@PathVariable Long id) {
        return employeeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/employees
    // Crea un nuevo empleado
    @PostMapping
    public ResponseEntity<Employee> crear(@RequestBody Employee empleado) {
        try {
            Employee guardado = employeeService.crearEmpleado(empleado);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/employees/{id}
    // Actualiza un empleado existente
    @PutMapping("/{id}")
    public ResponseEntity<Employee> actualizar(
            @PathVariable Long id,
            @RequestBody Employee empleadoDetalles) {
        try {
            Employee actualizado = employeeService.actualizarEmpleado(id, empleadoDetalles);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/employees/{id}
    // Elimina un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            employeeService.eliminarEmpleado(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/employees/search/high-salary?salary=50000
    // Busca empleados con salario alto
    @GetMapping("/search/high-salary")
    public ResponseEntity<List<Employee>> buscarSalarioAlto(
            @RequestParam(value = "salary", defaultValue = "10000") BigDecimal salario) {
        List<Employee> empleados = employeeService.obtenerEmpleadosConSalarioAlto(salario);
        return ResponseEntity.ok(empleados);
    }

    // GET /api/employees/search/by-name?apellido=King
    // Busca empleados por apellido
    @GetMapping("/search/by-name")
    public ResponseEntity<List<Employee>> buscarPorApellido(
            @RequestParam(value = "apellido", defaultValue = "") String apellido) {
        List<Employee> empleados = employeeService.buscarPorApellido(apellido);
        return ResponseEntity.ok(empleados);
    }
}
