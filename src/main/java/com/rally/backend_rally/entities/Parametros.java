package com.rally.backend_rally.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Etiqueta que nos indica que esto es una entidad JPA
@Table(name = "parametros") // Objeto que va a mapear la tabla parametros en la bbdd
public class Parametros {
	
    @Id // Etiqueta que indica que es una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera automáticamente 
    private Long id;
      
   	@Column(nullable = false)
    private Integer numMaxFotografias;
    
   	@Column(nullable = false)
    private String tema;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fInicioInscripcion;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fFinInscripcion;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fInicioVotacion;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fFinVotacion;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fGanador;

    // Relación uno-a-muchos con Categorias
    @OneToMany(mappedBy = "rally", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // evitar que se produzcan un bucle infinito
    private List<Categorias> categorias = new ArrayList<>();  
    
    //Constructor vacío
    public Parametros() {
		super();
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumMaxFotografias() {
		return numMaxFotografias;
	}

	public void setNumMaxFotografias(Integer numMaxFotografias) {
		this.numMaxFotografias = numMaxFotografias;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public LocalDate getfInicioInscripcion() {
		return fInicioInscripcion;
	}

	public void setfInicioInscripcion(LocalDate fInicioInscripcion) {
		this.fInicioInscripcion = fInicioInscripcion;
	}

	public LocalDate getfFinInscripcion() {
		return fFinInscripcion;
	}

	public void setfFinInscripcion(LocalDate fFinInscripcion) {
		this.fFinInscripcion = fFinInscripcion;
	}

	public LocalDate getfInicioVotacion() {
		return fInicioVotacion;
	}

	public void setfInicioVotacion(LocalDate fInicioVotacion) {
		this.fInicioVotacion = fInicioVotacion;
	}

	public LocalDate getfFinVotacion() {
		return fFinVotacion;
	}

	public void setfFinVotacion(LocalDate fFinVotacion) {
		this.fFinVotacion = fFinVotacion;
	}

	public LocalDate getfGanador() {
		return fGanador;
	}

	public void setfGanador(LocalDate fGanador) {
		this.fGanador = fGanador;
	}

	public List<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categorias> categorias) {
		this.categorias = categorias;
	}
}
