package com.rally.backend_rally.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rally.backend_rally.entities.Parametro;
import com.rally.backend_rally.services.ParametroService;

// Endpoint que el frontend usa para obtener los parametros del rally
@RestController // Esta etiqueta nos indica que esto es un Controlador. Maneja peticiones HTTP y devuelve respuestas JSON
@RequestMapping("/parametros") // Prefijo para las rutas de este controlador
public class ParametroController  {
    @Autowired
    private ParametroService parametrosService;// Servicio para acceder a los parametros

    /**
     * Endpoint GET para peticion a la base de datos de los parametros
     */
    @GetMapping
    public ResponseEntity<Parametro> obtenerParametros() {
        Parametro parametro = parametrosService.findAll();// Llama al método findAll del servicio para obtener los parámetros
        return ResponseEntity.ok(parametro);// Devuelve una respuesta OK + los datos
    }
    
    /**
     * Endpoint PUT para modificar los datos de los parametros de un rally
     */
    @PutMapping
    public ResponseEntity<Parametro> editarParametros(@RequestBody Parametro parametroEditar){
    	Parametro parametro = parametrosService.update(parametroEditar);
		return ResponseEntity.ok(parametro);
    }
    
}
