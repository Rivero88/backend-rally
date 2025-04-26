package com.rally.backend_rally.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Categoria;

@Repository // Es un repositorio para la entidad Categorias
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
}