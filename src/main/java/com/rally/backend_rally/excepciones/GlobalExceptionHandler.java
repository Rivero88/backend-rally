package com.rally.backend_rally.excepciones;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {
   
    @ExceptionHandler({ValidarFormatoException.class, ValidarTamannoException.class, ValidarVacioException.class, ValidarNombreCategoria.class, ParametroNoGuardado.class, ImagenNoGuardada.class,
    	UsuarioNoGuardado.class, CategoriaNoGuardada.class})
    public ResponseEntity<Map<String, String>> handlerBadRequestException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage()));
    }
    
    @ExceptionHandler({UsuarioNoEncontradoException.class, CategoriaNoEncontradaException.class, ValidarAliasException.class, ParametroNoEncontradoException.class, ValidarEmailException.class,
    	ImagenNoEncontradaException.class})
    public ResponseEntity<Map<String, String>> handlerNotFoundException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }
    
    @ExceptionHandler(ErrorEliminarCategoria.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", ex.getMessage()));
    }
    
    @ExceptionHandler(LoginContrasennaIncorrecto.class)
    public ResponseEntity<Map<String, String>> handleLoginIncorrecto(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", ex.getMessage()));
    }

    // Para manejar cualquier otro error genérico (opcional)
    @ExceptionHandler({Exception.class, FicheroNoValidoException.class})
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Error inesperado del servidor."));
    }
    
}
