package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Parametros;
import com.rally.backend_rally.entities.User;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Rol findRolByAlias(String alias) {
		Optional<User> userOptional = userRepository.findByAlias(alias);
		return userOptional.isPresent() ? userOptional.get().getRol() : Rol.participante;
	}
	
	public List<User> findAll() {
	    List<User> usuarios = userRepository.findAll();
	    return usuarios != null ? usuarios : new ArrayList<>();
	}
	
}
