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

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
	private String nombre;
	
	@Column(nullable = false)
    private String descripcion;
    
	// Relación n:1 con Entidad Parametro
	@ManyToOne
    @JoinColumn(name = "rally_id", nullable = false) // Clave foránea
	@JsonBackReference // Evita que se produzcan un bucle infinito
    private Parametro rally;
	
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

	public Parametro getRally() {
		return rally;
	}

	public void setRally(Parametro rally) {
		this.rally = rally;
	}
}
