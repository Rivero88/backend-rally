package com.rally.backend_rally.excepciones;

public class ParametroNoGuardado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParametroNoGuardado() {
		super("No se pudo guardar la modificación del parámetro.");
	}
}