package com.rally.backend_rally.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Usuario;

@Repository // Esta etiqueta indica que es un repositorio para la entidad User
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Query Methods para generar las consultas automáticamente
	 */
	// Busca un usuario por su alias. Retorna un Optional<User> por si no se encuentra.
    Optional<Usuario> findByAlias(String alias);
    // Busca un usuario por su email.
    Optional<Usuario> findByEmail(String email);
}