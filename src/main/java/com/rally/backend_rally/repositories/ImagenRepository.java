package com.rally.backend_rally.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Imagen;

@Repository // Es un repositorio para la entidad Imagen
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
	
	/**
	 * Consulta para traer las imagenes de un usuario
	 * @param usuarioId
	 * @return
	 */
	List<Imagen> findByUsuarioId(Long usuarioId);

	/**
	 * Consulta para traer las imagenes con un estado determinado
	 * @param string
	 * @return
	 */
	List<Imagen> findByEstadoValidacion(String string); 
}