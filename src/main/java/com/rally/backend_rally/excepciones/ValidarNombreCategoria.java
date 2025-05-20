package com.rally.backend_rally.excepciones;

public class ValidarNombreCategoria extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidarNombreCategoria() {
		super("Categoria ya registrado con el nombre indicado.");
	}
}