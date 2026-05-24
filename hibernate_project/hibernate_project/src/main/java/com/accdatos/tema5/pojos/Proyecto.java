package com.accdatos.tema5.pojos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa la tabla "proyecto" (actividad 5.7).
 * Un proyecto puede desarrollarse en varias sedes y una sede puede tener
 * varios proyectos, de ahi la relacion muchos-a-muchos a traves de la
 * tabla intermedia "proyecto_sede".
 */
@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proy")
    private Integer idProy;

    // El nombre debe ser unico; la restriccion se añade con ALTER TABLE en MySQL
    @Column(name = "nom_proy", nullable = false)
    private String nomProy;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    // Relacion muchos-a-muchos con Sede mediante la tabla proyecto_sede.
    // joinColumns referencia a este proyecto e inverseJoinColumns a la sede.
    @ManyToMany
    @JoinTable(
            name = "proyecto_sede",
            joinColumns = @JoinColumn(name = "id_proy"),
            inverseJoinColumns = @JoinColumn(name = "id_sede")
    )
    private List<Sede> sedes = new ArrayList<>();

    public Proyecto() {
    }

    public Proyecto(String nomProy, LocalDate fechaInicio) {
        this.nomProy = nomProy;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdProy() {
        return idProy;
    }

    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }

    public String getNomProy() {
        return nomProy;
    }

    public void setNomProy(String nomProy) {
        this.nomProy = nomProy;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    @Override
    public String toString() {
        return "Proyecto{idProy=" + idProy + ", nomProy='" + nomProy + "', fechaInicio=" + fechaInicio + "}";
    }
}
