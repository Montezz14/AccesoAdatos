package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
    // Job usa String como ID (como "IT_PROG")
}
