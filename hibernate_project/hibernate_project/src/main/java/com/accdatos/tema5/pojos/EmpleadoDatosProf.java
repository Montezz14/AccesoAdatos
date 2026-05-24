package com.accdatos.tema5.pojos;

import jakarta.persistence.*;

/**
 * Entidad que representa la tabla "empleado_datos_prof" (actividad 5.7).
 * Guarda informacion profesional de cada empleado (categoria y sueldo).
 * Comparte la clave primaria con Empleado: la relacion es uno-a-uno y el
 * DNI hace de clave primaria y a la vez de clave foranea (@MapsId).
 */
@Entity
@Table(name = "empleado_datos_prof")
public class EmpleadoDatosProf {

    // El DNI es a la vez clave primaria y foranea hacia empleado
    @Id
    @Column(name = "dni")
    private String dni;

    // @MapsId indica que la PK de esta entidad se toma del empleado asociado
    @OneToOne
    @MapsId
    @JoinColumn(name = "dni")
    private Empleado empleado;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "sueldo")
    private Double sueldo;

    public EmpleadoDatosProf() {
    }

    public EmpleadoDatosProf(Empleado empleado, String categoria, Double sueldo) {
        this.empleado = empleado;
        this.categoria = categoria;
        this.sueldo = sueldo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "EmpleadoDatosProf{dni='" + dni + "', categoria='" + categoria + "', sueldo=" + sueldo + "}";
    }
}
