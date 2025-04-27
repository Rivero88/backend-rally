package com.rally.backend_rally.excepciones;

public class ValidarFormatoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarFormatoException() {
		super("Formato de imagen no permitido (solo JPG, JPEG o PNG).");
	}
}