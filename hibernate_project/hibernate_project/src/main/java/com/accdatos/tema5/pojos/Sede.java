package com.accdatos.tema5.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa la tabla "sede" de la base de datos proyecto_orm.
 * Cada sede tiene un identificador autogenerado y un nombre, y mantiene
 * la relacion uno-a-muchos con sus departamentos.
 */
@Entity
@Table(name = "sede")
public class Sede {

    // Clave primaria autogenerada por la base de datos (AUTO_INCREMENT en MySQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede")
    private Integer idSede;

    // Nombre de la sede. En la actividad 5.6 se le añade un indice unico.
    @Column(name = "nom_sede", nullable = false)
    private String nomSede;

    // Una sede contiene varios departamentos. mappedBy apunta al atributo
    // "sede" de la clase Departamento, que es quien posee la clave foranea.
    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
    private List<Departamento> departamentos = new ArrayList<>();

    // Constructor vacio obligatorio para Hibernate
    public Sede() {
    }

    public Sede(String nomSede) {
        this.nomSede = nomSede;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    // toString para poder imprimir la sede de forma legible en la consola
    @Override
    public String toString() {
        return "Sede{idSede=" + idSede + ", nomSede='" + nomSede + "'}";
    }
}
