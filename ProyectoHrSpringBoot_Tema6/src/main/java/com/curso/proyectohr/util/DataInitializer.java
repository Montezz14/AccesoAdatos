package com.curso.proyectohr.util;

import com.curso.proyectohr.dominio.Usuario;
import com.curso.proyectohr.repositorios.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// @Component hace que Spring ejecute este código al arrancar
// Implements CommandLineRunner para ejecutar código en el startup
@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo creamos el usuario admin si no existe ya
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            // Ciframos la contraseña con BCrypt antes de guardarla
            String passwordCifrada = passwordEncoder.encode("admin");

            Usuario admin = new Usuario("admin", passwordCifrada, "ADMIN");
            usuarioRepository.save(admin);

            System.out.println("✓ Usuario 'admin' creado en la base de datos");
            System.out.println("  Usuario: admin");
            System.out.println("  Contraseña: admin (en texto plano, almacenada como hash)");
        }
    }
}
