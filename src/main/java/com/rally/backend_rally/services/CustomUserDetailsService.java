package com.rally.backend_rally.services;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository userRepository) {
        this.usuarioRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
    	Usuario user = usuarioRepository.findByAlias(alias)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getAlias());
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRol().name());  // Asumiendo que el rol es un campo String en la base de datos

        return userBuilder.build();
    }
}
