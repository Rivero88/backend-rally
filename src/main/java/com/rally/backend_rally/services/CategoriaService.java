package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Categoria;
import com.rally.backend_rally.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriasRepository;
	
	/**
	 * Método que realiza una búsqueda en la bbdd de todos las categorias.
	 * @return lista de categorias o lista vacía
	 */
	public List<Categoria> findAll() {
	    List<Categoria> categorias = categoriasRepository.findAll();
	    return categorias != null ? categorias : new ArrayList<>();
	}
	

	
}
