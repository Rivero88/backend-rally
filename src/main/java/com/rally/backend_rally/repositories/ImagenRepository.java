package com.rally.backend_rally.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Imagen;

@Repository // Es un repositorio para la entidad Imagen
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
	
	@Query("SELECT DISTINCT i.categorias.id FROM Imagen i WHERE i.usuario.id = :usuarioId")
	List<Long> findCategoriasConFotoByUsuario(@Param("usuarioId") Long usuarioId);

	List<Imagen> findByUsuarioId(Long usuarioId); 
}