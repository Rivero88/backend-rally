package com.rally.backend_rally.excepciones;

public class UsuarioNoGuardado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNoGuardado() {
		super("No se pudo guardar el usuario.");
	}
}