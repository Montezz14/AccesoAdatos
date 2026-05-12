package com.curso.proyectohr.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// @Entity indica que esta clase representa una tabla en la BD
// @Table especifica el nombre exacto de la tabla en la BD
@Entity
@Table(name = "regions")
@Data // Lombok genera getters, setters, toString, equals, hashCode automáticamente
@NoArgsConstructor // Constructor vacío (obligatorio para JPA/Hibernate)
@AllArgsConstructor // Constructor con todos los parámetros
public class Region {

    // @Id marca este campo como clave primaria
    // @GeneratedValue indica que el ID se genera automáticamente (autoincrement)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    // @Column mapea exactamente con el nombre de la columna en la BD
    @Column(name = "region_name", nullable = false, length = 25)
    private String regionName;

    // Excluimos este campo del toString para evitar bucles infinitos en relaciones
    @ToString.Exclude
    private transient Object relacionesOmitidas;
}
