package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.entities.Voto;
import com.rally.backend_rally.entities.VotoRanking;
import com.rally.backend_rally.services.VotoService;


@RestController
@RequestMapping("/votos")
public class VotoController  {
    @Autowired
    private VotoService votoService;
    
    /**
     * Método para votar imagen
     * @param imagenId
     * @param idUsuario
     * @return
     */
    @PutMapping("{imagenId}/{idUsuario}")
    public Voto votarImagen (@PathVariable Long imagenId, @PathVariable Long idUsuario) {
    	 return votoService.votarImagen(imagenId, idUsuario);
    }
    
    /**
     * Método para comprobar si un usuario ha votado ya una imagen
     * @param imagenId
     * @param idUsuario
     * @return
     */
    @GetMapping("/comprobar")
    public Boolean comprobarVotoUsuario (@RequestParam Long imagenId, @RequestParam(required = false) Long idUsuario) {
    	return votoService.comprobarVotoUsuarioImagen(imagenId, idUsuario);
    }
    
    /**
     * Listar los votos de una imagen
     * @param imagenId
     * @return
     */
    @GetMapping("/obtenerVotos/{imagenId}")
    public List<Voto> obtenerVotosImagen (@PathVariable Long imagenId){ 	
		return votoService.obtenerVotosImagen(imagenId);
    }
    
    @DeleteMapping("/{votoId}")
    public void eliminarVoto(@PathVariable Long votoId) {
    	votoService.deleteVoto(votoId);
    }
    
    /**
     * 
     * @return
     */
    @GetMapping("/votosRanking")
    public ResponseEntity<List<VotoRanking>> votosPorFecha() {
        return ResponseEntity.ok(votoService.obtenerVotosPorFecha());
    }
    
    
}
