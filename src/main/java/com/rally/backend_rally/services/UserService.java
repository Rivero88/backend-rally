package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rally.backend_rally.Util;
import com.rally.backend_rally.entities.User;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 
	 * @param alias
	 * @return
	 */
	public User findByAlias(String alias) {
		Optional<User> userOptional = userRepository.findByAlias(alias);
		return userOptional.isPresent() ? userOptional.get() : new User();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<User> findAll() {
	    List<User> usuarios = userRepository.findAll();
	    return usuarios != null ? usuarios : new ArrayList<>();
	}
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	public User findUserById(Long idUsuario) {
		Optional<User> userOptional = userRepository.findById(idUsuario);
		return userOptional.isPresent() ? userOptional.get() : new User();
	}

	/**
	 * 
	 * @param idUsuario
	 */
	public void deleteUser(Long idUsuario) {
		userRepository.deleteById(idUsuario);
	}
	
	/**
	 * 
	 */
	public User update(User usuarioEditar) {
		User usuario = userRepository.save(usuarioEditar);
		return usuario;
	}
	
	/**
	 * 
	 * @param idUsuario
	 * @param contrasennaNueva
	 */
	public void actualizarPassword(Long idUsuario, String contrasennaNueva) {
		Optional<User> userOptional = userRepository.findById(idUsuario);
		if(userOptional.isPresent()) {
			User usuario = userOptional.get();
			String contrasennaEncriptada = Util.encriptarPassword(contrasennaNueva);
			usuario.setPassword(contrasennaEncriptada);
			userRepository.save(usuario);
		}	
	}
	
	/**
	 * 
	 * @param usuarioNuevo
	 * @return
	 */
	public User nuevoUsuario(User usuarioNuevo) {
		Optional<User> userOptional = userRepository.findByAlias(usuarioNuevo.getAlias());
		if(userOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,("Usuario ya registrado con el Alias indicado."));
		}
		usuarioNuevo.setRol(Rol.participante);
		String contrasennaEncriptada = Util.encriptarPassword(usuarioNuevo.getPassword());
		usuarioNuevo.setPassword(contrasennaEncriptada);		
		User usuario = userRepository.save(usuarioNuevo);
		return usuario;
	}
	
}
