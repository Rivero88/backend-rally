package com.rally.backend_rally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.config.JwtUtil;
import com.rally.backend_rally.dto.AuthDto;
import com.rally.backend_rally.entities.LoginRequest;
import com.rally.backend_rally.entities.User;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.services.UserService;

// Endpoint que el frontend usa para autenticarse y recibir un JWT y el rol del usuario
@RestController // Esta etiqueta nos indica que esto es un Controlador
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager; // Manejador de autenticación
    
    @Autowired
    private JwtUtil jwtUtil; // Para generar y validar tokens
    
    @Autowired
    private UserService userService; // Servicio para acceder a usuarios y roles

    /**
     * Endpoint POST para login
     * Recibe un alias y contraseña, autentica, y devuelve un token y el rol
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginRequest request) {
    	// 1. Autentica las credenciales con Spring Security
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getAlias(), request.getPassword()));
        // 2. Si no lanza excepción, genera un JWT para ese alias. Obtiene el rol del usuario desde el servicio
        String token = jwtUtil.generateToken(request.getAlias());
        User user = userService.findByAlias(request.getAlias());
        // 3. Devuelve un DTO con el token y el rol del usuario
        return ResponseEntity.ok(new AuthDto(token, user.getRol(), user.getId()));
    }
}
