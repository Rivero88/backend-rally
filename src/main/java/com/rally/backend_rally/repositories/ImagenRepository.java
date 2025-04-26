package com.rally.backend_rally.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Imagen;

@Repository // Es un repositorio para la entidad Imagen
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
	
}