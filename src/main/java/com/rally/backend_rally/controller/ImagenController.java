package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    /**
     *  Endpoint POST para cargar imagenes.
     * @param imagenRequest
     * @return
     */
    @PostMapping("/cargar")
    public ResponseEntity<?> cargarImagen(@ModelAttribute ImagenRequest imagenRequest){
    	Imagen imagenGuardada = imagenService.guardarImagen(imagenRequest);
        return ResponseEntity.ok(imagenGuardada);
    }
    
    /**
     * Endpoint GET para sacar las categorias de fotografías ocupadas por id de usuario.
     * @param usuarioId
     * @return
     */
    @GetMapping("/cargar/categorias_ocupadas/{usuarioId}")
    public List<Long> obtenerCategoriasConFoto(@PathVariable Long usuarioId) {
    	return imagenService.obtenerCategoriasConFoto(usuarioId);
    }
    
    /**
     * Endpoint GET para obtener las imagenes de un usuario por un id.
     * @param usuarioId
     * @return
     */
    @GetMapping("/listar/{usuarioId}")
    public List<Imagen> obtenerImagenesUsuario(@PathVariable Long usuarioId){
    	return imagenService.obtenerImagenesUsuario(usuarioId);
    }
    
    /**
     * Endpoint DELETE para petición de borrado a la base de datos de una imagen
     * @param imagenId
     */
    @DeleteMapping("/{imagenId}")
    public void eliminarImagen(@PathVariable Long imagenId) {
    	imagenService.borrarImagen(imagenId);
    }
}
