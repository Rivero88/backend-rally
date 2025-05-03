package com.rally.backend_rally.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Categoria;

@Repository // Es un repositorio para la entidad Categorias
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	/**
	 * Consulta para traer las categorias con foto en funcion del usuario
	 * @param usuarioId
	 * @return
	 */
	@Query("SELECT DISTINCT i.categoria.id FROM Imagen i WHERE i.usuario.id = :usuarioId")
	List<Long> findCategoriasConFotoByUsuario(@Param("usuarioId") Long usuarioId);
	
}