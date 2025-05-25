package com.rally.backend_rally.excepciones;

public class VotoNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VotoNoEncontradoException() {
		super("Voto no encontrado.");
	}
}