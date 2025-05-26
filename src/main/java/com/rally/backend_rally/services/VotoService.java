package com.rally.backend_rally.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.entities.Voto;
import com.rally.backend_rally.entities.VotoRanking;
import com.rally.backend_rally.excepciones.UsuarioNoEncontradoException;
import com.rally.backend_rally.excepciones.VotoNoEncontradoException;
import com.rally.backend_rally.repositories.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
    private ImagenService imagenService;
	
	@Autowired
    private UsuarioService usuarioService;
	
	/**
	 * Se vota una imagen
	 * @param imagenId
	 * @param idUsuario
	 * @return
	 */
	public Voto votarImagen(Long imagenId, Long idUsuario) {
		Voto voto = new Voto();
		Imagen imagen = imagenService.seleccionarImagen(imagenId);
		Usuario usuario = usuarioService.findUserById(idUsuario);
		if(usuario.getId() == null) {
			throw new UsuarioNoEncontradoException();
		}
		voto.setImagen(imagen);
		voto.setUsuario(usuario);
		voto.setFecha(LocalDate.now());
		return votoRepository.save(voto);
	}
	
	/**
	 * Comprueba si un usuario ha votado una imagen
	 * @param imagenId
	 * @param idUsuario
	 * @return
	 */
	public Boolean comprobarVotoUsuarioImagen(Long imagenId, Long idUsuario) {
		Optional<Voto> voto = votoRepository.findByImagenIdAndUsuarioId(imagenId, idUsuario);
		return voto.isPresent();
	}
	
	/**
	 * Obtiene los boton de una imagen
	 * @param imagenId
	 * @return
	 */
	public List<Voto> obtenerVotosImagen(Long imagenId){
		List<Voto> votos = votoRepository.findByImagenId(imagenId);
		return votos;
	}

	/**
	 * Borra un voto
	 * @param votoId
	 */
	public void deleteVoto(Long votoId) {
		try {
			votoRepository.deleteById(votoId);
		}catch(Exception e) {
			throw new VotoNoEncontradoException();
		}
		
	}

	/**
	 * 
	 * @return
	 */
	public List<VotoRanking> obtenerVotosPorFecha() {
	    List<Object[]> resultados = votoRepository.contarVotosPorFecha();
	    return resultados.stream()
	    		.map(obj -> new VotoRanking(((java.sql.Date) obj[0]).toLocalDate(), ((Number) obj[1]).intValue()))
	        .collect(Collectors.toList());
	}
}
