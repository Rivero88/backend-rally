package com.rally.backend_rally.excepciones;

public class ImagenNoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImagenNoEncontradaException() {
		super("Imagen no encontrada.");
	}
}