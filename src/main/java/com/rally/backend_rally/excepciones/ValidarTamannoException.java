package com.rally.backend_rally.excepciones;

public class ValidarTamannoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarTamannoException() {
		super("El tamaño máximo permitido es de 2MB.");
	}
}