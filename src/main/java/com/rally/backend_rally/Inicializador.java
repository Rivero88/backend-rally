package com.rally.backend_rally;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.enums.Rol;
import com.rally.backend_rally.services.UsuarioService;

@Component
public class Inicializador {
	
    private final UsuarioService usuarioService;

    @Autowired
    public Inicializador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAdminUserIfNotExists() {
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
            usuarioService.guardar(admin);
            System.out.println("Usuario administrador creado por defecto.");
        }
    }
}
