package com.rally.backend_rally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rally.backend_rally.config.JwtUtil;
import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.ImagenRequest;
import com.rally.backend_rally.entities.LoginRequest;
import com.rally.backend_rally.services.ImagenService;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin(origins = "*") // Permitir Angular local dev
public class ImagenController {
	
    @Autowired
    private ImagenService imagenService;

    @PostMapping("/cargar")
    public ResponseEntity<?> cargarImagen(@ModelAttribute ImagenRequest imagenRequest) {
        try {
            Imagen imagenGuardada = imagenService.guardarImagen(imagenRequest);

            return ResponseEntity.ok(imagenGuardada);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
