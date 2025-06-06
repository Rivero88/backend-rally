package com.rally.backend_rally.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String descripcion;
    
    @Column(nullable = false)
    private String formato;
    
    @Column(nullable = false)
    private Long tamanno;
    
    @Column(nullable = false)
    private String estadoValidacion = "Pendiente";
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate fechaSubida;
    
    private String url;
    
    // Relación n:1 con Entidad Categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)// Clave foránea. No puede ser null
    private Categoria categoria; 

    // Relación n:1 con Entidad Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)// Clave foránea. No puede ser null
    private Usuario usuario;

    /**
     * Relación 1:n con entidad Voto.
     * mappedBy=la clave foránea está en la entidad Voto.
     * CascadeType.ALL=las operaciones de imagen se propagan a sus Votos. Por ejemplo, si elimino imagen, se eliminan sus votos.
     * orphanRemoval= si se elimina un voto, se elimina también de la bbdd.
     */
    @OneToMany(mappedBy = "imagen", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Voto> votos = new ArrayList<>(); 
    
    @Transient
    public Integer getVotosImagen() {
    	return this.getVotos().size();
    }
    
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

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Long getTamanno() {
		return tamanno;
	}

	public void setTamanno(Long tamanno) {
		this.tamanno = tamanno;
	}

	public String getEstadoValidacion() {
		return estadoValidacion;
	}

	public void setEstadoValidacion(String estadoValidacion) {
		this.estadoValidacion = estadoValidacion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(LocalDate fechaSubida) {
		this.fechaSubida = fechaSubida;
	}


	public List<Voto> getVotos() {
		return votos;
	}


	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
    
}
