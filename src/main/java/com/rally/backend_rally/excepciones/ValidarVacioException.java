package com.rally.backend_rally.excepciones;

public class ValidarVacioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarVacioException() {
		super("El archivo está vacío.");
	}
}