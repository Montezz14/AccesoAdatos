package com.curso.proyectohr.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Entidad que representa un país
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    // En este caso el ID es un código VARCHAR, no un Long
    @Id
    @Column(name = "country_id", length = 2)
    private String id;

    @Column(name = "country_name", length = 40)
    private String countryName;

    // @ManyToOne indica que muchos países pertenecen a una región
    // @JoinColumn especifica la columna que hace de clave foránea
    @ManyToOne
    @JoinColumn(name = "region_id")
    // Excluimos esto del toString para evitar serialización circular
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Region region;
}
