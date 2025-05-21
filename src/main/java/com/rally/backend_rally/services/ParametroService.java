package com.rally.backend_rally.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Parametro;
import com.rally.backend_rally.excepciones.ParametroNoEncontradoException;
import com.rally.backend_rally.excepciones.ParametroNoGuardado;
import com.rally.backend_rally.repositories.ParametroRepository;

@Service
public class ParametroService {

	@Autowired
	private ParametroRepository parametroRepository;
	
	/**
	 * Método que realiza una búsqueda en la bbdd de todos los parámetros.
	 * @return parametro o lista vacía
	 */
	public Parametro findAll() {
		List<Parametro> parametro = parametroRepository.findAll();
		return parametro != null && !parametro.isEmpty() ? parametro.get(0) : new Parametro();
	}
	/**
	 * Método para modificar parametros del rally
	 * @param parametroEditar
	 * @return
	 */
	public Parametro update(Parametro parametroEditar) {
		Optional<Parametro> parametroOptional = parametroRepository.findById(parametroEditar.getId());
		if(!parametroOptional.isPresent()) {
			throw new ParametroNoEncontradoException();
		}
		try {
			Parametro parametro = parametroRepository.save(parametroEditar);
			return parametro;
		}catch (Exception e){
			throw new ParametroNoGuardado();
		}
	}
}
