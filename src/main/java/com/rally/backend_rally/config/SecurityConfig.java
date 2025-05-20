package com.rally.backend_rally.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;

import com.rally.backend_rally.services.CustomUserDetailsService;


@Configuration // Esta etiqueta marca esta clase como una clase de configuración
@EnableWebSecurity // Habilita la configuración de seguridad web
public class SecurityConfig{
	
	// Se inyecta el servicio que carga los datos del usuario
	private final CustomUserDetailsService customUserDetailsService;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	// Constructor para inyectar el servicio
	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
	                      JwtAuthenticationFilter jwtAuthenticationFilter) {
	    this.customUserDetailsService = customUserDetailsService;
	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	    
    /**
     * Configura la cadena de filtros de seguridad (qué rutas se protegen, etc.)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults()) 
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF porque se usan Tokens
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**","/parametros", "/usuarios/registrar", "/imagenes/listar", "/imagenes/obtenerImagen/**", "/imagenes/votar/**", "/imagenes/ranking/**" ).permitAll()
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    /**
     * Configura el AuthenticationManager, que maneja el proceso de autenticación
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(customUserDetailsService)  // Usamos el CustomUserDetailsService
                   .passwordEncoder(passwordEncoder())         // Configuramos el PasswordEncoder
                   .and()
                   .build();
    }

    /**
     * Define el codificador de contraseñas: encripta y valida con BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200","https://backend-rally-production-a980.up.railway.app"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Si necesitas enviar cookies o cabeceras como Authorization

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
  
}
