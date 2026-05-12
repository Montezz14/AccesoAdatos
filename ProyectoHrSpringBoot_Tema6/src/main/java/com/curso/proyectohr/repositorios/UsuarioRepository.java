package com.curso.proyectohr.repositorios;

import com.curso.proyectohr.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar un usuario por su nombre de usuario
    // Usamos Optional porque el usuario podría no existir
    Optional<Usuario> findByUsername(String username);
}
