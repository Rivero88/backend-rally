package com.rally.backend_rally;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Util {
	 public static String encryptPassword(String password) {
		 PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 return passwordEncoder.encode(password);
	 }
}
