package com.rally.backend_rally.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.rally.backend_rally.entities.Categoria;
import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.ImagenRequest;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.excepciones.CategoriaNoEncontradaException;
import com.rally.backend_rally.excepciones.UsuarioNoEncontradoException;
import com.rally.backend_rally.excepciones.ValidarFormatoException;
import com.rally.backend_rally.excepciones.ValidarTamannoException;
import com.rally.backend_rally.excepciones.ValidarVacioException;
import com.rally.backend_rally.repositories.CategoriaRepository;
import com.rally.backend_rally.repositories.ImagenRepository;
import com.rally.backend_rally.repositories.UsuarioRepository;

@Service
public class ImagenService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ImagenRepository imagenRepository;
	
	@Value("${ruta.fotografias}")
	private String rutaGlobal;
	
	@Transactional
	public Imagen guardarImagen(ImagenRequest imagenRequest) {
        MultipartFile archivo = imagenRequest.getFile();

        if (archivo.isEmpty()) {
           throw new ValidarVacioException();
        }

        // Validar tamaño (2MB máximo)
        if (archivo.getSize() > 2 * 1024 * 1024) {
            throw new ValidarTamannoException();
        }

        // Validar formato
        String tipo = archivo.getContentType();
        if (tipo == null || (!tipo.equals("image/jpeg") && !tipo.equals("image/png") && !tipo.equals("image/jpg"))) {
            throw new ValidarFormatoException();
        }

        Imagen imagen = new Imagen();
        imagen.setNombre(imagenRequest.getNombre());
        imagen.setDescripcion(imagenRequest.getDescripcion());
        imagen.setFormato(tipo);
        imagen.setTamanno(archivo.getSize());
        imagen.setVotos(0);
        imagen.setEstadoValidacion("Pendiente");
        
        // Set Categoria
        Categoria categoria = categoriaRepository.findById(imagenRequest.getCategoriaId())
                .orElseThrow(() -> new CategoriaNoEncontradaException());
        imagen.setCategorias(categoria);

        // Set Usuario
        Usuario usuario = usuarioRepository.findById(imagenRequest.getUsuarioId())
                .orElseThrow(() -> new UsuarioNoEncontradoException());
        imagen.setUsuario(usuario);
        
        // Guarda la imagen en la bbdd
        Imagen imagenGuardada = imagenRepository.save(imagen);
        
        // Para poner nombre a la imagen
        String nombreArchivo = imagenGuardada.getId().toString() + obtenerExtension(archivo);
        
        // Para crear el path de la imagen y la carpeta dónde se guarda la imagen
        String uploadDir = rutaGlobal + imagen.getUsuario().getId() + "/" + imagen.getCategorias().getId(); // Ruta dónde se guardan las imagenes
        java.io.File uploadPath = new java.io.File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs(); // Se crea la carpeta si no existe
        }
        // Setea la url de la imagen que tenemos en la bbdd con la path creada arriba
        imagenGuardada.setUrl(imagen.getUsuario().getId() + "/" + imagen.getCategorias().getId() + "/" + nombreArchivo); 
        
        // Guarda la imagen en la bbdd
        Imagen imagenGuardadaFinal = imagenRepository.save(imagenGuardada);
        
        java.nio.file.Path path = java.nio.file.Paths.get(uploadDir + "/" + nombreArchivo);
        try {
			archivo.transferTo(path.toFile());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return imagenGuardadaFinal;
    }

	private String obtenerExtension(MultipartFile archivo) {
		String nombreOriginalImagen = archivo.getOriginalFilename();
        String extension = "";

        if (nombreOriginalImagen != null && nombreOriginalImagen.contains(".")) {
            extension = nombreOriginalImagen.substring(nombreOriginalImagen.lastIndexOf(".") + 1);
        }
		return "." + extension;
	}
	
	public List<Long> obtenerCategoriasConFoto(Long usuarioId) {
        return imagenRepository.findCategoriasConFotoByUsuario(usuarioId);
    }
}
