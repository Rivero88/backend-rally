package com.rally.backend_rally;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String rawPassword = "admin1";
        String rawPassword = "parti1";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Contraseña cifrada: " + encodedPassword);
    }
}
