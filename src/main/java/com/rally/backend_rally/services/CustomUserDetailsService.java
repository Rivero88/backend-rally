package com.rally.backend_rally.services;

import com.rally.backend_rally.entities.User;
import com.rally.backend_rally.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        User user = userRepository.findByAlias(alias)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getAlias());
        userBuilder.password(user.getPassword());
        //userBuilder.roles(user.getRole());  // Asumiendo que el rol es un campo String en la base de datos

        return userBuilder.build();
    }
}
