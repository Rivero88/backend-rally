package com.rally.backend_rally.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Clase para los datos de las categorias del Rally
@Entity // Etiqueta que nos indica que esto es una entidad JPA
@Table(name = "categorias") // Objeto que va a mapear la tabla categorias en la bbdd
public class Categorias {
	
	@Id // Etiqueta que indica que es una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente 
    private Long id;
	
	@Column(nullable = false, unique = true)
	private String nombre;
	
	@Column(nullable = false)
    private String descripcion;
    
	@ManyToOne
    @JoinColumn(name = "rally_id", nullable = false) // Clave foránea
	@JsonBackReference // evitar que se produzcan un bucle infinito
    private Parametros rally;
	
	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Parametros getRally() {
		return rally;
	}

	public void setRally(Parametros rally) {
		this.rally = rally;
	}
}
