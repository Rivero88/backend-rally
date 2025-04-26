package com.rally.backend_rally.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Parametro;
import com.rally.backend_rally.repositories.ParametroRepository;

@Service
public class ParametroService {

	@Autowired
	private ParametroRepository paramentroRepository;
	
	/**
	 * Método que realiza una búsqueda en la bbdd de todos los parámetros.
	 * @return parametro o lista vacía
	 */
	public Parametro findAll() {
		List<Parametro> parametro = paramentroRepository.findAll();
		return parametro != null && !parametro.isEmpty() ? parametro.get(0) : new Parametro();
	}
	
	public Parametro update(Parametro parametroEditar) {
		Parametro parametro = paramentroRepository.save(parametroEditar);
		return parametro;
	}
}
