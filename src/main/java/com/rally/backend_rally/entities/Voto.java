package com.rally.backend_rally.entities;

import java.time.LocalDate;

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
@Table(name = "votos")
public class Voto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate fecha = LocalDate.now();

    // Relación n:1 con Entidad Usuario
    @ManyToOne(optional = true)
    @JoinColumn(name = "usuario_id", nullable = false) // Clave foránea
    private Usuario usuario;
    
    // Relación n:1 con Entidad Imagen
    @ManyToOne(optional = false)
    @JoinColumn(name = "imagen_id", nullable = false) // Clave foránea
	@JsonBackReference // Evita que se produzcan un bucle infinito
    private Imagen imagen;

    // Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}
}
