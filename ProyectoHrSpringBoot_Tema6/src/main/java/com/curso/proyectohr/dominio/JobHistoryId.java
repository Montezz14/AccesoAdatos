package com.curso.proyectohr.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

// Esta clase representa una clave primaria compuesta
// En la tabla job_history, la PK es (employee_id, start_date)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryId implements Serializable {

    // Debe coincidir exactamente con el nombre del atributo en JobHistory
    private Long employee;
    
    private LocalDate startDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobHistoryId that = (JobHistoryId) o;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        return startDate != null ? startDate.equals(that.startDate) : that.startDate == null;
    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }
}
