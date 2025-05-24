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
import com.rally.backend_rally.entities.Auth;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.excepciones.LoginContrasennaIncorrecto;
import com.rally.backend_rally.request.LoginRequest;
import com.rally.backend_rally.services.UsuarioService;

// Endpoint que el frontend usa para autenticarse y recibir un JWT y el rol del usuario
@RestController // Esta etiqueta nos indica que esto es un Controlador
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager; // Manejador de autenticación
    
    @Autowired
    private JwtUtil jwtUtil; // Para generar y validar tokens
    
    @Autowired
    private UsuarioService usuarioService; // Servicio para acceder a usuarios y roles

    /**
     * Endpoint POST para login
     * Recibe un alias y contraseña, autentica, y devuelve un token, el rol y el id
     */
    @PostMapping("/login")
    public ResponseEntity<Auth> login(@RequestBody LoginRequest request) {
    	try {
    		// 1. Autentica las credenciales con Spring Security
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getAlias(), request.getPassword()));
    	}catch(Exception e) {
    		throw new LoginContrasennaIncorrecto();
    	}
        // 2. Si no lanza excepción, genera un JWT para ese alias. Obtiene el usuario
        String token = jwtUtil.generateToken(request.getAlias());
        Usuario user = usuarioService.findByAlias(request.getAlias());
        // 3. Devuelve un DTO con el token, el rol y el id del usuario
        return ResponseEntity.ok(new Auth(token, user.getRol(), user.getId()));
    }
}
