package com.rally.backend_rally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.entities.Voto;
import com.rally.backend_rally.services.VotoService;

// Endpoint que el frontend usa para obtener los votos del rally
@RestController // Esta etiqueta nos indica que esto es un Controlador. Maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/votos") // Prefijo para las rutas de este controlador
public class VotoController  {
    @Autowired
    private VotoService votoService;// Servicio para acceder a los votos
    
    @PutMapping("{imagenId}/{idUsuario}")
    public Voto votarImagen (@PathVariable Long imagenId, @PathVariable Long idUsuario) {
    	 return votoService.votarImagen(imagenId, idUsuario);
    }
    
    @GetMapping("/comprobar")
    public Boolean comprobarVotoUsuario (@RequestParam Long imagenId, @RequestParam(required = false) Long idUsuario) {
    	return votoService.comprobarVotoUsuarioImagen(imagenId, idUsuario);
    }
    
}
