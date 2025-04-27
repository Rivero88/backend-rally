package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rally.backend_rally.Util;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.excepciones.ValidarAliasException;
import com.rally.backend_rally.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;
	
	/**
	 * Método que realiza una búsqueda en la bbdd mediante el alias.
	 * @param alias
	 * @return Optional de tipo User o nuevo usuario vacío
	 */
	public Usuario findByAlias(String alias) {
		Optional<Usuario> userOptional = userRepository.findByAlias(alias);
		return userOptional.isPresent() ? userOptional.get() : new Usuario();
	}
	
	/**
	 * Método que realiza una búsqueda en la bbdd de todos los usuarios.
	 * @return lista de usuarios o lista vacía
	 */
	public List<Usuario> findAll() {
	    List<Usuario> usuarios = userRepository.findAll();
	    return usuarios != null ? usuarios : new ArrayList<>();
	}
	
	/**
	 * Método que realiza una búsqueda en la bbdd mediante el id.
	 * @param idUsuario
	 * @return Optional de tipo User o nuevo usuario vacío
	 */
	public Usuario findUserById(Long idUsuario) {
		Optional<Usuario> userOptional = userRepository.findById(idUsuario);
		return userOptional.isPresent() ? userOptional.get() : new Usuario();
	}

	/**
	 * Método que borrar un usuario de la bbdd mediante el id.
	 * @param idUsuario
	 */
	public void deleteUser(Long idUsuario) {
		userRepository.deleteById(idUsuario);
	}
	
	/**
	 *  Método que guarda un usuario que ha sido modificado.
	 * @return usuario
	 */
	public Usuario update(Usuario usuarioEditar) {
		Usuario usuario = userRepository.save(usuarioEditar);
		return usuario;
	}
	
	/**
	 * Método que modifica la contraseña de un usuario.
	 * @param idUsuario
	 * @param contrasennaNueva
	 */
	public void actualizarPassword(Long idUsuario, String contrasennaNueva) {
		Optional<Usuario> userOptional = userRepository.findById(idUsuario);
		if(userOptional.isPresent()) {
			Usuario usuario = userOptional.get();
			String contrasennaEncriptada = Util.encriptarPassword(contrasennaNueva);
			usuario.setPassword(contrasennaEncriptada);
			userRepository.save(usuario);
		}	
	}
	
	/**
	 * Método que guarda un usuario nuevo.
	 * @param usuarioNuevo
	 * @return usuario
	 */
	public Usuario nuevoUsuario(Usuario usuarioNuevo) {
		Optional<Usuario> userOptional = userRepository.findByAlias(usuarioNuevo.getAlias());
		if(userOptional.isPresent()) {
			throw new ValidarAliasException();
		}
		usuarioNuevo.setRol(Rol.participante);
		String contrasennaEncriptada = Util.encriptarPassword(usuarioNuevo.getPassword());
		usuarioNuevo.setPassword(contrasennaEncriptada);		
		Usuario usuario = userRepository.save(usuarioNuevo);
		return usuario;
	}
	
}
