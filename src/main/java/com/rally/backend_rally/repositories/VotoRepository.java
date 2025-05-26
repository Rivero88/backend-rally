package com.rally.backend_rally.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.Voto;

@Repository // Es un repositorio para la entidad Parametros
public interface VotoRepository  extends JpaRepository<Voto, Long> {
	
	/**
	 * Se consulta una imagen y se comprueba si el usuario logueado ha votado esa imagen.
	 * @param imagenId
	 * @param usuarioId
	 * @return
	 */
	 Optional<Voto> findByImagenIdAndUsuarioId(Long imagenId, Long usuarioId );

	 /**
	  * Consulta para contar los votos para una imagen
	  * @param imagen
	  * @return
	  */
	 int countByImagen(Imagen imagen);

	 /**
	  * Consulta para listar los votos de una imagen
	  * @param imagenId
	  * @return
	  */
	 List<Voto> findByImagenId(Long imagenId);
	 
	 /**
	  * 
	  * @return
	  */
	 @Query("SELECT FUNCTION('DATE', v.fecha) AS fecha, COUNT(v) AS totalVotos FROM Voto v GROUP BY FUNCTION('DATE', v.fecha) ORDER BY fecha")
	 List<Object[]> contarVotosPorFecha();
}