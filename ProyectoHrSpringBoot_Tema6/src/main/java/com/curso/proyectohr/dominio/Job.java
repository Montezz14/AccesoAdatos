package com.curso.proyectohr.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Representa un puesto de trabajo (IT_PROG, AD_PRES, etc.)
@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    // El ID es un código VARCHAR (como "IT_PROG")
    @Id
    @Column(name = "job_id", length = 10)
    private String id;

    @Column(name = "job_title", nullable = false, length = 35)
    private String jobTitle;

    // Salario mínimo para este puesto
    @Column(name = "min_salary")
    private Double minSalary;

    // Salario máximo para este puesto
    @Column(name = "max_salary")
    private Double maxSalary;
}
