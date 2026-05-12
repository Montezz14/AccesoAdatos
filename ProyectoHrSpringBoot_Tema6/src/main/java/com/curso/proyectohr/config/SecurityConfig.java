package com.curso.proyectohr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configuración de seguridad con Spring Security
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Bean del codificador de contraseñas - BCrypt es seguro y lento a propósito
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Proveedor de autenticación que usa nuestra BD
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Cadena de filtros de seguridad - Define las reglas de acceso
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivar CSRF por simplicidad (en producción sería necesario)
                .csrf(csrf -> csrf.disable())
                
                // Autorización HTTP
                .authorizeHttpRequests(auth -> auth
                        // URLs públicas
                        .requestMatchers("/", "/index.html", "/styles.css", "/app.js", "/images/**").permitAll()
                        // Rutas protegidas
                        .requestMatchers("/api/**", "/employees.html").authenticated()
                        // Cualquier otra cosa también autenticada
                        .anyRequest().authenticated()
                )
                
                // Login por formulario (para el navegador)
                .formLogin(form -> form
                        .defaultSuccessUrl("/index.html", true)
                        .permitAll()
                )
                
                // Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index.html")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                
                // Autenticación básica (para Postman y herramientas similares)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
