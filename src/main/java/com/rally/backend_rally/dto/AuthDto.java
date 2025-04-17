package com.rally.backend_rally.dto;

import com.rally.backend_rally.enums.Rol;

public class AuthDto {
	private String token;
	
	private Rol rol;
	
	public AuthDto(String token, Rol rol) {
		super();
		this.token = token;
		this.rol = rol;
	}
	
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
	
}

 
