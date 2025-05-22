package com.rally.backend_rally.excepciones;

public class ImagenNoGuardada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImagenNoGuardada() {
		super("No se pudo guardar la modificación de la imagen.");
	}
}