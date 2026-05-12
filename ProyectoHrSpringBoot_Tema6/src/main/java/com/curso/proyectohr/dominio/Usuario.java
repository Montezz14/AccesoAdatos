package com.curso.proyectohr.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta tabla almacena los usuarios del sistema (para login)
// Es diferente de la tabla employees que son los empleados de la empresa
@Entity
@Table(name = "app_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username es único en el sistema
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    // Password se almacena cifrada (hasheada) con BCrypt
    @Column(nullable = false, length = 255)
    private String password;

    // El rol del usuario (ADMIN, USER, etc.)
    @Column(length = 20)
    private String role;

    // Constructor para facilitar la creación de usuarios
    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
