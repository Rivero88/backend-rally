package com.rally.backend_rally.repositories;


import com.rally.backend_rally.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Esta etiqueta indica que es un repositorio para la entidad User
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Query Methods para generar las consultas automáticamente
	 */
	// Busca un usuario por su alias. Retorna un Optional<User> por si no se encuentra.
    Optional<User> findByAlias(String alias);
    // Busca un usuario por su email.
    Optional<User> findByEmail(String email);
}