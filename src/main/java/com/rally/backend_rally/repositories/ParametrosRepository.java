package com.rally.backend_rally.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Parametros;

@Repository // Esta etiqueta indica que es un repositorio para la entidad Parametros
public interface ParametrosRepository  extends JpaRepository<Parametros, Long> {

}