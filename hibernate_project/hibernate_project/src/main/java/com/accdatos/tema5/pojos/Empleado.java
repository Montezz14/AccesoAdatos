package com.accdatos.tema5.pojos;

import jakarta.persistence.*;

/**
 * Entidad que representa la tabla "empleado".
 * La clave primaria es el DNI (un String), por lo que no se autogenera.
 * Cada empleado pertenece a un departamento.
 */
@Entity
@Table(name = "empleado")
public class Empleado {

    // El DNI es la clave primaria natural, asi que no lleva @GeneratedValue
    @Id
    @Column(name = "dni")
    private String dni;

    @Column(name = "nom_emp", nullable = false)
    private String nomEmp;

    // Muchos empleados pertenecen a un departamento.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_depto", nullable = false)
    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(String dni, String nomEmp, Departamento departamento) {
        this.dni = dni;
        this.nomEmp = nomEmp;
        this.departamento = departamento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        Integer idDepto = (departamento != null) ? departamento.getIdDepto() : null;
        return "Empleado{dni='" + dni + "', nomEmp='" + nomEmp + "', idDepto=" + idDepto + "}";
    }
}
