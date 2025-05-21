package com.rally.backend_rally.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Parametro;

@Repository
public interface ParametroRepository  extends JpaRepository<Parametro, Long> {

	Optional<Parametro> findByTema();

}