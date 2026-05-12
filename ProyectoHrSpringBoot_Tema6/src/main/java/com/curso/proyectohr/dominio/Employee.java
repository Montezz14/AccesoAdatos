package com.curso.proyectohr.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDate;

// Representa un empleado de la empresa
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 25)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    // Usamos BigDecimal para dinero en lugar de Double (más precisión)
    @Column(name = "salary", precision = 8, scale = 2)
    private BigDecimal salary;

    // Porcentaje de comisión del empleado
    @Column(name = "commission_pct", precision = 2, scale = 2)
    private BigDecimal commissionPct;

    // Relación con Job (puesto de trabajo)
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Job job;

    // Relación con Department (departamento)
    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    // Relación recursiva con otro empleado (el jefe)
    // Un empleado puede tener un manager que es otro empleado
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee manager;
}
