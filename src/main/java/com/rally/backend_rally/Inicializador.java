package com.rally.backend_rally;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rally.backend_rally.entities.Parametro;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.services.ParametroService;
import com.rally.backend_rally.services.UsuarioService;

@Component
public class Inicializador {
	
    private final UsuarioService usuarioService;
    private final ParametroService parametroService;

    @Autowired
    public Inicializador(UsuarioService usuarioService, ParametroService parametroService) {
        this.usuarioService = usuarioService;
        this.parametroService = parametroService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void crearDatosIniciales() {
    	crearUsuarioAdmin();
    	crearParametrosIniciales();
    }

	private void crearUsuarioAdmin() {
		Usuario usuario = usuarioService.findByAlias("admin1");
        if (usuario.getId() == null) {
            Usuario admin = new Usuario();
            admin.setAlias("admin1");
            admin.setNombre("Alejo");
            admin.setApellidos("Gonzalez Romero");
            admin.setfNacimiento(LocalDate.of(1988,9,19));
            admin.setNumTelefono(698547856);
            admin.setEmail("admin1@gmail.com");
            admin.setPassword("$2a$10$3smbmFlYsjdViYGMn6kEZeQ.kTIVWJiKu1uY7.a5QTTE0.ylg2HDK");
            admin.setRol(Rol.admin);
            usuarioService.guardarUsuarioAdmin(admin);
        }
	}
	
	private void crearParametrosIniciales() {
		Parametro parametro = parametroService.findByTema("Vacaciones de ensueño");
		if(parametro.getId() == null) {
			Parametro rally = new Parametro();
			rally.setNumMaxFotografias(1);
			rally.setTema("Vacaciones de ensueño");
			rally.setfInicioInscripcion(LocalDate.of(2025,4,01));
			rally.setfInicioVotacion(LocalDate.of(2025,4,01));
			rally.setfFinInscripcion(LocalDate.of(2025,8,31));
			rally.setfFinVotacion(LocalDate.of(2025,8,31));
			rally.setfGanador(LocalDate.of(2025,9,15));
			parametroService.guardar(rally);
		}
		
	}
}
