package com.rally.backend_rally.excepciones;

public class ParametroNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParametroNoEncontradoException() {
		super("Parametro no encontrado.");
	}
}