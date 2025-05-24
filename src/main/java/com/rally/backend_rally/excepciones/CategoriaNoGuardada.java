package com.rally.backend_rally.excepciones;

public class CategoriaNoGuardada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoriaNoGuardada() {
		super("No se pudo guardar la categoria.");
	}
}