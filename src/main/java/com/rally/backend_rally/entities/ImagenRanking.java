package com.rally.backend_rally.entities;

public class ImagenRanking  {
	private Long id;
    private String nombre;
    private String categoria;
    private String autor;
    private int votos;
	
	// Constructor
    public ImagenRanking(Long id, String nombre, String categoria, String autor, int votos) {
		super();
		this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.autor = autor;
        this.votos = votos;
	}
    
	// Getter y setter
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
}

 
