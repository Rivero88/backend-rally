package com.rally.backend_rally.excepciones;

public class FicheroNoValidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FicheroNoValidoException() {
		super("El fichero no se ha podido procesar correctamente.");
	}
}