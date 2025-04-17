package com.rally.backend_rally.repositories;


import com.rally.backend_rally.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Métodos personalizados si los necesitas
    Optional<User> findByAlias(String alias);
    Optional<User> findByEmail(String email);
}