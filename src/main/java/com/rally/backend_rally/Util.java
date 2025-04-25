package com.rally.backend_rally;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Util {

	/**
	 * Método para encriptar contraseña cuando se hace petición de modificación
	 * @param password
	 * @return una password codificada
	 */
	 public static String encriptarPassword(String password) {
		 //PasswordEncoder y BCryptPasswordEncoder son clases de java
		 PasswordEncoder passwordCodificada = new BCryptPasswordEncoder();
		 return passwordCodificada.encode(password);
	 }
}
