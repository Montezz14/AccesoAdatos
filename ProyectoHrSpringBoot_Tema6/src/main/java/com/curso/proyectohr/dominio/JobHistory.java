package com.curso.proyectohr.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

// Registra el historial de cambios de puesto de un empleado
// Tiene una clave primaria compuesta (employee_id + start_date)
@Entity
@Table(name = "job_history")
@IdClass(JobHistoryId.class) // Indicamos que usa una PK compuesta
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory {

    // Primera parte de la PK compuesta
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;

    // Segunda parte de la PK compuesta
    @Id
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // Job del historial
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Job job;

    // Department del historial
    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;
}
