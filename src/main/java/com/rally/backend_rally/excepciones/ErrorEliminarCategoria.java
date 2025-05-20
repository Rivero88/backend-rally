package com.rally.backend_rally.excepciones;

public class ErrorEliminarCategoria extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorEliminarCategoria() {
		super("No se puede eliminar la categoría porque tiene imágenes asociadas.");
	}
}