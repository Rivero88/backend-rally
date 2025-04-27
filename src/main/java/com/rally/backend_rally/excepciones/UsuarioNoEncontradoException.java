package com.rally.backend_rally.excepciones;

public class UsuarioNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoException() {
		super("Usuario no encontrado.");
	}
}