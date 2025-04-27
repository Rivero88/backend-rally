package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.ImagenRequest;
import com.rally.backend_rally.excepciones.ValidarFormatoException;
import com.rally.backend_rally.services.ImagenService;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin(origins = "*") // Permitir Angular local dev
public class ImagenController {
	
    @Autowired
    private ImagenService imagenService;

    @PostMapping("/cargar")
    public ResponseEntity<?> cargarImagen(@ModelAttribute ImagenRequest imagenRequest){
    	Imagen imagenGuardada = imagenService.guardarImagen(imagenRequest);
        return ResponseEntity.ok(imagenGuardada);
    }
    
    @GetMapping("/cargar/categorias_ocupadas/{usuarioId}")
    public List<Long> obtenerCategoriasConFoto(@PathVariable Long usuarioId) {
    	return imagenService.obtenerCategoriasConFoto(usuarioId);
    }
}
