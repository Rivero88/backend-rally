package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.Util;
import com.rally.backend_rally.entities.User;
import com.rally.backend_rally.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findByAlias(String alias) {
		Optional<User> userOptional = userRepository.findByAlias(alias);
		return userOptional.isPresent() ? userOptional.get() : new User();
	}
	
	public List<User> findAll() {
	    List<User> usuarios = userRepository.findAll();
	    return usuarios != null ? usuarios : new ArrayList<>();
	}
	
	public User findUserById(Long idUsuario) {
		Optional<User> userOptional = userRepository.findById(idUsuario);
		return userOptional.isPresent() ? userOptional.get() : new User();
	}

	public void deleteUser(Long idUsuario) {
		userRepository.deleteById(idUsuario);
	}
	
	public User update(User usuarioEditar) {
		User usuario = userRepository.save(usuarioEditar);
		return usuario;
	}
	
	public void updatePassword(Long idUsuario, String contrasennaNueva) {
		Optional<User> userOptional = userRepository.findById(idUsuario);
		if(userOptional.isPresent()) {
			User usuario = userOptional.get();
			String contrasennaEncriptada = Util.encryptPassword(contrasennaNueva);
			usuario.setPassword(contrasennaEncriptada);
			userRepository.save(usuario);
		}	
	}
	
}
