package com.rally.backend_rally.excepciones;

public class ValidarAliasException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarAliasException() {
		super("Usuario ya registrado con el Alias indicado.");
	}
}