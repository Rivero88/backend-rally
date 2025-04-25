package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * Endpoint GET para petición a la base de datos de los usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> obtenerUsuarios() {
    	List<User> usuarios = userService.findAll();// Llama al método findAll del servicio para obtener los usuarios
        return ResponseEntity.ok(usuarios);// Devuelve una respuesta OK + los datos
    }
    
    /**
     * Endpoint GET para petición a la base de datos de un usuario
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<User> obtenerUsuarioId(@PathVariable Long idUsuario){
    	User usuario = userService.findUserById(idUsuario);
    	return ResponseEntity.ok(usuario);
    }
    
    /**
     * Endpoint DELETE para petición de borrado a la base de datos de un usuario
     */
    @DeleteMapping("/{idUsuario}")
    public void eliminarUsuario(@PathVariable Long idUsuario) {
    	userService.deleteUser(idUsuario);
    }
    
    /**
     * Endpoint PUT para modificar los datos de un usuario en la base de datos
     */
    @PutMapping
    public ResponseEntity<User> editarUsuario(@RequestBody User usuarioEditar){
    	User usuario = userService.update(usuarioEditar);
		return ResponseEntity.ok(usuario);
    }
    
    /**
     * Endpoint PUT para modificar la contraseña de un usuario en la base de datos
     */
    @PutMapping("/modificarContrasenna")
    public void editarContrasenna(@RequestParam Long idUsuario, String contrasennaNueva) {
    	userService.actualizarPassword(idUsuario, contrasennaNueva);
    }
    
    /**
     * Endpoint POST para ingresar los datos de un usuario nuevo en la base de datos
     */
    @PostMapping("/registrar")
    public ResponseEntity<User> nuevoUsuario(@RequestBody User usuarioNuevo){
    	User usuario = userService.nuevoUsuario(usuarioNuevo);
		return ResponseEntity.ok(usuario);
    }
    
}
