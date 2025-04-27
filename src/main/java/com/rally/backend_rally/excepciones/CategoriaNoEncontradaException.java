package com.rally.backend_rally.excepciones;

public class CategoriaNoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoriaNoEncontradaException() {
		super("Categoría no encontrada.");
	}
}