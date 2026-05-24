package com.accdatos.tema5.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa la tabla "departamento".
 * Cada departamento pertenece a una sede (relacion muchos-a-uno) y
 * agrupa a varios empleados (relacion uno-a-muchos).
 */
@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depto")
    private Integer idDepto;

    @Column(name = "nom_depto", nullable = false)
    private String nomDepto;

    // Muchos departamentos pueden pertenecer a una misma sede.
    // La columna id_sede es la clave foranea hacia la tabla sede.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede", nullable = false)
    private Sede sede;

    // Un departamento tiene varios empleados.
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Empleado> empleados = new ArrayList<>();

    public Departamento() {
    }

    public Departamento(String nomDepto, Sede sede) {
        this.nomDepto = nomDepto;
        this.sede = sede;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        // Mostramos solo el id de la sede para evitar recorridos infinitos
        Integer idSede = (sede != null) ? sede.getIdSede() : null;
        return "Departamento{idDepto=" + idDepto + ", nomDepto='" + nomDepto + "', idSede=" + idSede + "}";
    }
}
