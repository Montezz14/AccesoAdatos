package com.curso.proyectohr.excepciones;

// Excepción personalizada cuando no encontramos un recurso
// Extiende RuntimeException para que no obligue a capturarla con try-catch
public class RecursoNoEncontradoException extends RuntimeException {
    
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public RecursoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
