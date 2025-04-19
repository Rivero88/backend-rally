package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.entities.Parametros;
import com.rally.backend_rally.entities.User;
import com.rally.backend_rally.services.ParametrosService;
import com.rally.backend_rally.services.UserService;

// Endpoint que el frontend usa para obtener los usuarios
@RestController // Esta etiqueta nos indica que esto es un Controlador. Maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/usuarios") // Prefijo para las rutas de este controlador
public class UserController  {
    @Autowired
    private UserService userService;// Servicio para acceder a los usuarios

    /**
     * Endpoint GET para peticion a la base de datos de los usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> obtenerUsuarios() {
    	List<User> usuario = userService.findAll();// Llama al método findAll del servicio para obtener los usuarios
        return ResponseEntity.ok(usuario);// Devuelve una respuesta OK + los datos
    }
}
