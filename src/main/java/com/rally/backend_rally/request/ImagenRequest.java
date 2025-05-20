package com.rally.backend_rally.request;

import org.springframework.web.multipart.MultipartFile;

// 
public class ImagenRequest {
	private String nombre;
    private String descripcion;
    private Long categoriaId;
    private Long usuarioId;
    private Integer votos;
    private Integer tamanno;
    private String formato;
    private String estadoValidacion;
    private MultipartFile file;
    
    // Getters y Setters
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
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getVotos() {
		return votos;
	}
	public void setVotos(Integer votos) {
		this.votos = votos;
	}
	public Integer getTamanno() {
		return tamanno;
	}
	public void setTamanno(Integer tamanno) {
		this.tamanno = tamanno;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getEstadoValidacion() {
		return estadoValidacion;
	}
	public void setEstadoValidacion(String estadoValidacion) {
		this.estadoValidacion = estadoValidacion;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
