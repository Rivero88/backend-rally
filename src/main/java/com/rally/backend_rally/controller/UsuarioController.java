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

import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.services.UsuarioService;

// Endpoint que el frontend usa para gestión de los usuarios

@RestController // Controlador. Maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/usuarios") // Prefijo para las rutas de este controlador
public class UsuarioController  {
    @Autowired
    private UsuarioService usuarioService;// Servicio para acceder a los usuarios

   /**
    * Endpoint GET para petición a la base de datos de los usuarios.
    * Llama al método findAll del servicio para obtener los usuarios
    * @return respuesta OK + los datos
    */
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
    	List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
	 * Endpoint DELETE para petición de borrado a la base de datos de un usuario
	 * Llama al método deleteUser del servicio para borrar un usuario por su id
	 * @param idUsuario
	 */
    @DeleteMapping("/{idUsuario}")
    public void eliminarUsuario(@PathVariable Long idUsuario) {
    	usuarioService.deleteUser(idUsuario);
    }
    
	/**
	 * Endpoint GET para petición a la base de datos de un usuario.
	 * Llama al método findUserById del servicio para obtener un usuario por su id
	 * @param idUsuario
	 * @return respuesta OK + los datos
	 */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> obtenerUsuarioId(@PathVariable Long idUsuario){
    	Usuario usuario = usuarioService.findUserById(idUsuario);
    	return ResponseEntity.ok(usuario);
    }
     
	/**
	 * Endpoint PUT para modificar los datos de un usuario en la base de datos
	 * Llama al método update del servicio para modificar un usuario
	 * @param usuarioEditar
	 * @return respuesta OK + los datos
	 */
    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuarioEditar){
    	Usuario usuario = usuarioService.actualizar(usuarioEditar);
		return ResponseEntity.ok(usuario);
    }
    
	/**
	 * Endpoint PUT para modificar la contraseña de un usuario en la base de datos
	 * Llama al método actualizarPassword para modificar la contraseña de un usuario
	 * @param idUsuario
	 * @param contrasennaNueva
	 */
    @PutMapping("/modificarContrasenna")
    public void editarContrasenna(@RequestParam Long idUsuario, String contrasennaNueva) {
    	usuarioService.actualizarPassword(idUsuario, contrasennaNueva);
    }
    
	/**
	 * Endpoint POST para ingresar los datos de un usuario nuevo en la base de datos
	 * Llama al método nuevoUsuario del servicio para dar de alta un usuario
	 * @param usuarioNuevo
	 * @return respuesta OK + los datos
	 */
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> nuevoUsuario(@RequestBody Usuario usuarioNuevo){
    	Usuario usuario = usuarioService.nuevoUsuario(usuarioNuevo);
		return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("/registrarVoto")
    public ResponseEntity<Usuario> nuevoUsuarioVoto(@RequestBody Usuario usuarioNuevoVoto){
    	Usuario usuarioVoto = usuarioService.nuevoUsuarioVoto(usuarioNuevoVoto);
		return ResponseEntity.ok(usuarioVoto);
    }
    
}
