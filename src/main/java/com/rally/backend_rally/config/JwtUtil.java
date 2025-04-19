package com.rally.backend_rally.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//Para usar JWT (JSON Web Tokens) para las claves y contraseñas

@Component // Esta etiqueta marca esta clase como un componente
public class JwtUtil {
    
	/**
     * Genera un JWT con el alias (nombre de usuario)
     * y una expiración de 1 día (24 horas)
     */
    public String generateToken(String alias) {
        return Jwts.builder()
                .setSubject(alias)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extrae el alias desde un JWT
     */
    public String extractAlias(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    /**
     * Valida que el token pertenezca al usuario esperado
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractAlias(token).equals(userDetails.getUsername()); // Compara el alias del token con el alias del usuario autenticado
    }
    
    private static final String SECRET = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE3NDUwODMwODV9.LuIPHWBdmD-fV2AohhUXHExdc-GECCkIQr0RhdekgYd_2c02I0fPdjHi7FiwLG52siKH7vShmlROO7Sslh_qQA";

    /**
     * Utiliza una clave secreta para firmar/verificar los tokens
     */
    public SecretKey getSigningKey() {
        // Utiliza la clave fija que tenemos más arriba
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}
