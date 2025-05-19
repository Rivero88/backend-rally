package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.dto.ImagenRankingDto;
import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.request.ImagenRequest;
import com.rally.backend_rally.services.ImagenService;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin(origins = "*")
public class ImagenController {
	
    @Autowired
    private ImagenService imagenService;
    
    /**
     *  Endpoint POST para cargar imagenes.
     * @param imagenRequest
     * @return  respuesta OK + los datos
     */
    @PostMapping("/cargar")
    public ResponseEntity<Imagen> cargarImagen(@ModelAttribute ImagenRequest imagenRequest){
    	Imagen imagenGuardada = imagenService.guardarImagen(imagenRequest);
        return ResponseEntity.ok(imagenGuardada);
    }
        
    /**
     * Endpoint GET para obtener las imagenes de un usuario por un id.
     * @param usuarioId
     * @return lista de imagenes de usuario
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
    
    /**
     * Endpoint GET para obtener las imagenes de un usuario por un id y poder visualizarlas en el modal.
     * @param usuarioId
     * @return una imagen en formato byte
     */
    @GetMapping("/obtenerImagen/{imagenId}")
    public ResponseEntity<byte[]> obtenerImagenParaModal(@PathVariable Long imagenId){
    	return imagenService.obtenerImagenVer(imagenId);
    }
    
    /**
     * Endpoint GET para obtener una imagen por su id.
     * @param imagenId
     * @return una imagen
     */
    @GetMapping("/seleccionar/{imagenId}")
    public Imagen seleccionarImagen(@PathVariable Long imagenId){
    	return imagenService.seleccionarImagen(imagenId);
    }
    
    /**
     * Endpoint GET para obtener todas las imagenes.
     * @return una lista de imagenes
     */
    @GetMapping("/listar")
    public List<Imagen> listarTodasImagenes(){
    	return imagenService.listarTodasImagenes();
    }
    
    /**
     * Endpoint PATCH para cambiar estado a validado.
     * @param imagenId
     * @return
     */
    @PatchMapping("/validar/{imagenId}")
    public Imagen validarImagen(@PathVariable Long imagenId) {
    	return imagenService.validarImagen(imagenId);
    }
    
    /**
     * Endpoint PATCH para cambiar estado a rechazado.
     * @param imagenId
     * @return
     */
    @PatchMapping("/rechazar/{imagenId}")
    public Imagen rechazarImagen(@PathVariable Long imagenId) {
    	return imagenService.rechazarImagen(imagenId);
    }
    
    /**
     * Endpoint PUT para modificar una imagen.
     * @param imagenEditar
     * @return
     */
    @PutMapping
    public Imagen editarImagen(@RequestBody Imagen imagenEditar){
    	Imagen imagen = imagenService.actualizarImagen(imagenEditar);
		return imagen;
    } 
    
    /**
     * Endpoint GET para obtener un ranking de imagenes segun los votos, sólo de las fotos validadas.
     * @return
     */
    @GetMapping("/ranking")
    public ResponseEntity<List<ImagenRankingDto>> obtenerRanking() {
        return ResponseEntity.ok(imagenService.obtenerRankingImagenes());
    }
}
