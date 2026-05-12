package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.JobHistory;
import com.curso.proyectohr.dominio.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {
    // JobHistory usa una clave primaria compuesta (JobHistoryId)
}
