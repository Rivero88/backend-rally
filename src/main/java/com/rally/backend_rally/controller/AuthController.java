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
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getAlias(), request.getPassword()));
        String token = jwtUtil.generateToken(request.getAlias());
        Rol rol = userService.findRolByAlias(request.getAlias());
        return ResponseEntity.ok(new AuthDto(token, rol));
    }
}
