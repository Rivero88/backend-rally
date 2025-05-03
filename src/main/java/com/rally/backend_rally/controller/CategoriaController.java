package com.rally.backend_rally.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.entities.Categoria;
import com.rally.backend_rally.services.CategoriaService;

// Endpoint que el frontend usa para gestión de los usuarios

@RestController // Controlador. Maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/categorias") // Prefijo para las rutas de este controlador
public class CategoriaController  {
    @Autowired
    private CategoriaService categoriasService;// Servicio para acceder a las categorias

   /**
    * Endpoint GET para petición a la base de datos.
    * Llama al método findAll del servicio para obtener las categorias
    * @return respuesta OK + los datos
    */
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
    	List<Categoria> categorias = categoriasService.findAll();
        return ResponseEntity.ok(categorias);
    } 
    
    /**
     * Endpoint GET para sacar las categorias de fotografías ocupadas por id de usuario.
     * Llama al método obtenerCategoriasConFoto del servicio para obtener las categorias que ya tienen foto cargada
     * @param usuarioId
     * @return
     */
    @GetMapping("/cargar/categorias_ocupadas/{usuarioId}")
    public List<Long> obtenerCategoriasConFoto(@PathVariable Long usuarioId) {
    	return categoriasService.obtenerCategoriasConFoto(usuarioId);
    }
    
}
