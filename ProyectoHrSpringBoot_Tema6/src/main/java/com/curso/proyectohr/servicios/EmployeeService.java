package com.curso.proyectohr.servicios;

import com.curso.proyectohr.dominio.Employee;
import com.curso.proyectohr.repositorios.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// @Service indica que esta clase contiene la lógica de negocio
// Los servicios actúan como intermediarios entre controladores y repositorios
@Service
public class EmployeeService {

    // Inyectamos el repositorio para acceder a la BD
    private final EmployeeRepository employeeRepository;

    // Constructor para la inyección de dependencias
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // --- OPERACIONES CRUD ---

    // @Transactional asegura que la operación se ejecuta en una transacción
    // Si algo falla, se revierte todo (ROLLBACK)
    @Transactional
    public Employee crearEmpleado(Employee empleado) {
        // Aquí podríamos añadir validaciones de negocio
        if (empleado.getSalary() != null && empleado.getSalary().doubleValue() < 0) {
            throw new RuntimeException("El salario no puede ser negativo");
        }
        return employeeRepository.save(empleado);
    }

    // readOnly=true optimiza la transacción para solo lectura
    @Transactional(readOnly = true)
    public List<Employee> obtenerTodos() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Employee> obtenerPorId(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee actualizarEmpleado(Long id, Employee empleadoDetalles) {
        // Buscamos el empleado existente
        Employee empleado = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));

        // Actualizamos solo los campos que nos interesan
        if (empleadoDetalles.getFirstName() != null) {
            empleado.setFirstName(empleadoDetalles.getFirstName());
        }
        if (empleadoDetalles.getLastName() != null) {
            empleado.setLastName(empleadoDetalles.getLastName());
        }
        if (empleadoDetalles.getEmail() != null) {
            empleado.setEmail(empleadoDetalles.getEmail());
        }
        if (empleadoDetalles.getSalary() != null) {
            empleado.setSalary(empleadoDetalles.getSalary());
        }

        return employeeRepository.save(empleado);
    }

    @Transactional
    public void eliminarEmpleado(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Empleado no encontrado con ID " + id);
        }
        employeeRepository.deleteById(id);
    }

    // --- MÉTODOS DE CONSULTA (LÓGICA DE NEGOCIO) ---

    @Transactional(readOnly = true)
    public List<Employee> obtenerEmpleadosConSalarioAlto(BigDecimal salarioMinimo) {
        return employeeRepository.findBySalaryGreaterThan(salarioMinimo);
    }

    @Transactional(readOnly = true)
    public List<Employee> buscarPorApellido(String apellido) {
        return employeeRepository.findByLastNameContaining(apellido);
    }

    @Transactional(readOnly = true)
    public Long contarEmpleadosEnDepartamento(Long departmentId) {
        return employeeRepository.countByDepartmentId(departmentId);
    }
}
