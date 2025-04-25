package com.rally.backend_rally.dto;

import com.rally.backend_rally.enums.Rol;

//DTO (Data Transfer Object) que se usa para mandar al frontend el resultado del login: el token JWT y el rol 
public class AuthDto {
	private String token;
	
	private Rol rol;
	
	private Long idUsuario;
	
	// Constructor
	public AuthDto(String token, Rol rol, Long idUsuario) {
		super();
		this.token = token;
		this.rol = rol;
		this.idUsuario = idUsuario;
	}
	
	// Getter y setter
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}

 
