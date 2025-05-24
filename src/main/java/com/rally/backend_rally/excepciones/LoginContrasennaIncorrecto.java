package com.rally.backend_rally.excepciones;

public class LoginContrasennaIncorrecto extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginContrasennaIncorrecto() {
		super("Alias o Contraseña incorrecta.");
	}
}