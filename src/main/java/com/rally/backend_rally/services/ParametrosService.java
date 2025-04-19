package com.rally.backend_rally.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Parametros;
import com.rally.backend_rally.repositories.ParametrosRepository;

@Service
public class ParametrosService {

	@Autowired
	private ParametrosRepository paramentroRepository;
	
	public Parametros findAll() {
		List<Parametros> parametro = paramentroRepository.findAll();
		return parametro != null && !parametro.isEmpty() ? parametro.get(0) : new Parametros();
	}
	
	public Parametros update(Parametros parametroEditar) {
		Parametros parametro = paramentroRepository.save(parametroEditar);
		return parametro;
	}
}
