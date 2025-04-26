package com.rally.backend_rally.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Parametro;

@Repository // Es un repositorio para la entidad Parametros
public interface ParametroRepository  extends JpaRepository<Parametro, Long> {

}