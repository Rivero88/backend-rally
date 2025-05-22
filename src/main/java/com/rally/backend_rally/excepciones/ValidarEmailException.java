package com.rally.backend_rally.excepciones;

public class ValidarEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarEmailException() {
		super("Usuario ya registrado con el email indicado.");
	}
}