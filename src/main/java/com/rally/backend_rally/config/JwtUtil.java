package com.rally.backend_rally.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    public String generateToken(String alias) {
        return Jwts.builder()
                .setSubject(alias)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractAlias(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        return extractAlias(token).equals(userDetails.getUsername());
    }
    
    public SecretKey getSigningKey() {
        // Generar una clave secreta de 256 bits
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);  // Puedes usar HS512 si prefieres una clave más larga
    }
}
