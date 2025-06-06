package com.rally.backend_rally.entities;

// Clase para poder extraer y modificar contraseñas
public class ContrasennaUsuario {
    private Long idUsuario;
    private String contrasenna;

    // Constructor vacío
    public ContrasennaUsuario() {}

    // Constructor con parámetros
    public ContrasennaUsuario(Long idUsuario, String contrasenna) {
        this.idUsuario = idUsuario;
        this.contrasenna = contrasenna;
    }

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}
}
