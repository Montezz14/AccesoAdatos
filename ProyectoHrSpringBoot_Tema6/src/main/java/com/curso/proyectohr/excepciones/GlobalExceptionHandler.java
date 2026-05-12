package com.curso.proyectohr.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// @ControllerAdvice atrapa las excepciones de todos los controladores
// Nos permite enviar respuestas JSON bonitas en lugar de páginas de error HTML
@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja nuestra excepción personalizada
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(
            RecursoNoEncontradoException ex, WebRequest request) {

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());
        respuesta.put("error", "Recurso No Encontrado");
        respuesta.put("message", ex.getMessage());
        respuesta.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // Maneja cualquier otra excepción no capturada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepcionGlobal(
            Exception ex, WebRequest request) {

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("error", "Error Interno del Servidor");
        respuesta.put("message", ex.getMessage());
        respuesta.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
