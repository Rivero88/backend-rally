package com.rally.backend_rally.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Categoria;
import com.rally.backend_rally.entities.Parametro;
import com.rally.backend_rally.excepciones.CategoriaNoEncontradaException;
import com.rally.backend_rally.excepciones.ErrorEliminarCategoria;
import com.rally.backend_rally.excepciones.ValidarNombreCategoria;
import com.rally.backend_rally.repositories.CategoriaRepository;
import com.rally.backend_rally.repositories.ImagenRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	/**
	 * Método que realiza una búsqueda en la bbdd de todos las categorias.
	 * @return lista de categorias o lista vacía
	 */
	public List<Categoria> findAll() {
	    List<Categoria> categorias = categoriaRepository.findAll();
	    return categorias != null ? categorias : new ArrayList<>();
	}
	/**
	 * Método que realiza una búsqueda en la bbdd de todos las categorias con imagen cargada.
	 * @param usuarioId
	 * @return
	 */
	public List<Long> obtenerCategoriasConFoto(Long usuarioId) {
		return categoriaRepository.findCategoriasConFotoByUsuario(usuarioId);
	}
	
	public Categoria nuevaCategoria(Categoria categoriaNueva) {
		Parametro parametro = parametroService.findAll();
		Optional<Categoria> categoriaOptional = categoriaRepository.findByNombre(categoriaNueva.getNombre());
		if(categoriaOptional.isPresent()) {
			throw new ValidarNombreCategoria();
		}		
		categoriaNueva.setRally(parametro);
		Categoria categoria = categoriaRepository.save(categoriaNueva);
		return categoria;
	}
	
	public void eliminarCategoria(Long categoriaId) {
		// Verifica si existen imágenes asociadas
        if (imagenRepository.existsByCategoria_Id(categoriaId)) {
            throw new ErrorEliminarCategoria();
        }

        if (!categoriaRepository.existsById(categoriaId)) {
            throw new CategoriaNoEncontradaException();
        }
		categoriaRepository.deleteById(categoriaId);
	}
	
}
