package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.Util;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.excepciones.UsuarioNoEncontradoException;
import com.rally.backend_rally.excepciones.UsuarioNoGuardado;
import com.rally.backend_rally.excepciones.ValidarAliasException;
import com.rally.backend_rally.excepciones.ValidarEmailException;
import com.rally.backend_rally.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * Método que realiza una búsqueda en la bbdd mediante el alias.
	 * @param alias
	 * @return Optional de tipo User o nuevo usuario vacío
	 */
	public Usuario findByAlias(String alias) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByAlias(alias);
		return usuarioOptional.isPresent() ? usuarioOptional.get() : new Usuario();
	}
	
	/**
	 * Método que realiza una búsqueda en la bbdd de todos los usuarios.
	 * @return lista de usuarios o lista vacía
	 */
	public List<Usuario> findAll() {
	    List<Usuario> usuarios = usuarioRepository.findAll();
	    return usuarios != null ? usuarios : new ArrayList<>();
	}
	
	/**
	 * Método que realiza una búsqueda en la bbdd mediante el id.
	 * @param idUsuario
	 * @return Optional de tipo User o nuevo usuario vacío
	 */
	public Usuario findUserById(Long idUsuario) {
		Optional<Usuario> userOptional = usuarioRepository.findById(idUsuario);
		return userOptional.isPresent() ? userOptional.get() : new Usuario();
	}

	/**
	 * Método que borrar un usuario de la bbdd mediante el id.
	 * @param idUsuario
	 */
	public void deleteUser(Long idUsuario) {
		try {
			usuarioRepository.deleteById(idUsuario);
		}catch(Exception e) {
			throw new UsuarioNoEncontradoException();
		}
		
	}
	
	/**
	 *  Método que guarda un usuario que ha sido modificado.
	 * @return usuario
	 */
	public Usuario actualizar(Usuario usuarioEditar) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuarioEditar.getEmail());
		if(usuarioOptional.isPresent() && !usuarioOptional.get().getId().equals(usuarioEditar.getId())){
			throw new ValidarEmailException();
		}
		try {
			Usuario usuario = usuarioRepository.save(usuarioEditar);
			return usuario;
		}catch(Exception e) {
			throw new UsuarioNoGuardado();
		}
		
	}
	
	/**
	 * Método que modifica la contraseña de un usuario.
	 * @param idUsuario
	 * @param contrasennaNueva
	 */
	public void actualizarPassword(Long idUsuario, String contrasennaNueva) {
		Optional<Usuario> userOptional = usuarioRepository.findById(idUsuario);
		if(userOptional.isPresent()) {
			Usuario usuario = userOptional.get();
			String contrasennaEncriptada = Util.encriptarPassword(contrasennaNueva);
			usuario.setPassword(contrasennaEncriptada);
			usuarioRepository.save(usuario);
		}	
	}
	
	/**
	 * Método que guarda un usuario participante
	 * @param usuarioNuevo
	 * @return usuario
	 */
	public Usuario nuevoUsuario(Usuario usuarioNuevo) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByAlias(usuarioNuevo.getAlias());
		if(usuarioOptional.isPresent()) {
			throw new ValidarAliasException();
		}
		Optional<Usuario> usuarioOptional2 = usuarioRepository.findByEmail(usuarioNuevo.getEmail());
		if(usuarioOptional2.isPresent()){
			throw new ValidarEmailException();
		}
		usuarioNuevo.setRol(Rol.participante);
		String contrasennaEncriptada = Util.encriptarPassword(usuarioNuevo.getPassword());
		usuarioNuevo.setPassword(contrasennaEncriptada);
		try {
			Usuario usuario = usuarioRepository.save(usuarioNuevo);
			return usuario;
		}catch(Exception e) {
			throw new UsuarioNoGuardado();
		}
		
	}
	
	/**
	 * Método que guarda un usuario general
	 * @param usuarioNuevoVoto
	 * @return usuarioVoto
	 */
	public Usuario nuevoUsuarioVoto(Usuario usuarioNuevoVoto) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByAlias(usuarioNuevoVoto.getAlias());
		if(usuarioOptional.isPresent()) {
			throw new ValidarAliasException();
		}
		Optional<Usuario> usuarioOptional2 = usuarioRepository.findByEmail(usuarioNuevoVoto.getEmail());
		if(usuarioOptional2.isPresent()){
			throw new ValidarEmailException();
		}
		usuarioNuevoVoto.setRol(Rol.general);
		String contrasennaEncriptada = Util.encriptarPassword(usuarioNuevoVoto.getPassword());
		usuarioNuevoVoto.setPassword(contrasennaEncriptada);		
		try {
			Usuario usuarioVoto = usuarioRepository.save(usuarioNuevoVoto);
			return usuarioVoto;
		}catch(Exception e) {
			throw new UsuarioNoGuardado();
		}
	}
	
	/**
	 * Para guardar usuario admin del inicializador
	 * @param usuarioNuevo
	 * @return
	 */
	public Usuario guardarUsuarioAdmin(Usuario usuarioNuevo) {
		return usuarioRepository.save(usuarioNuevo);
	}
	
}
