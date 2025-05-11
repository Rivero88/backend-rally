package com.rally.backend_rally.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.entities.Voto;
import com.rally.backend_rally.repositories.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
    private ImagenService imagenService;
	
	@Autowired
    private UsuarioService usuarioService;
	
	public Voto votarImagen(Long imagenId, Long idUsuario) {
		Voto voto = new Voto();
		Imagen imagen = imagenService.seleccionarImagen(imagenId);
		Usuario usuario = usuarioService.findUserById(idUsuario);
		voto.setImagen(imagen);
		voto.setUsuario(usuario);
		voto.setFecha(LocalDate.now());
		return votoRepository.save(voto);
	}
	
	public Boolean comprobarVotoUsuarioImagen(Long imagenId, Long idUsuario) {
		Optional<Voto> voto = votoRepository.findByImagenIdAndUsuarioId(imagenId, idUsuario);
		return voto.isPresent();
	}
}
