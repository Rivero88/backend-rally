package com.rally.backend_rally.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rally.backend_rally.entities.Categoria;
import com.rally.backend_rally.entities.Imagen;
import com.rally.backend_rally.entities.ImagenRanking;
import com.rally.backend_rally.entities.Usuario;
import com.rally.backend_rally.excepciones.CategoriaNoEncontradaException;
import com.rally.backend_rally.excepciones.ImagenNoEncontradaException;
import com.rally.backend_rally.excepciones.ImagenNoGuardada;
import com.rally.backend_rally.excepciones.ParametroNoEncontradoException;
import com.rally.backend_rally.excepciones.ParametroNoGuardado;
import com.rally.backend_rally.excepciones.UsuarioNoEncontradoException;
import com.rally.backend_rally.excepciones.ValidarFormatoException;
import com.rally.backend_rally.excepciones.ValidarTamannoException;
import com.rally.backend_rally.excepciones.ValidarVacioException;
import com.rally.backend_rally.repositories.CategoriaRepository;
import com.rally.backend_rally.repositories.ImagenRepository;
import com.rally.backend_rally.repositories.UsuarioRepository;
import com.rally.backend_rally.repositories.VotoRepository;
import com.rally.backend_rally.request.ImagenRequest;

@Service
public class ImagenService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ImagenRepository imagenRepository;
	@Autowired
	private VotoRepository votoRepository;

	@Value("${ruta.fotografias}")
	private String rutaGlobal;

	/**
	 * Método para guardar la imagen seleccionada por el usuario.
	 * Se guarda en una carpeta con el nombre del id del usuario. Dentro tiene una carpeta con el id de la categoría.
	 * Dentro se encuentra la imagen con el nombre: idImagen.
	 * Es transaccional para que se haga todo a la vez (guardado en bbdd y en carpetas) y si no que no se haga nada.
	 * @param imagenRequest
	 * @return una imagen
	 */
	@Transactional
	public Imagen guardarImagen(ImagenRequest imagenRequest) {
		MultipartFile archivo = imagenRequest.getFile();

		// Validar si viene vacío
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

		// Se crea un objeto imagen y se setea
		Imagen imagen = new Imagen();
		imagen.setNombre(imagenRequest.getNombre());
		imagen.setDescripcion(imagenRequest.getDescripcion());
		imagen.setFormato(tipo);
		imagen.setTamanno(archivo.getSize());
		imagen.setEstadoValidacion("Pendiente");
		Categoria categoria = categoriaRepository.findById(imagenRequest.getCategoriaId())
				.orElseThrow(() -> new CategoriaNoEncontradaException());
		imagen.setCategoria(categoria);
		Usuario usuario = usuarioRepository.findById(imagenRequest.getUsuarioId())
				.orElseThrow(() -> new UsuarioNoEncontradoException());
		imagen.setUsuario(usuario);

		// Guarda la imagen en la bbdd
		Imagen imagenGuardada = imagenRepository.save(imagen);

		// Guarda la imagen en carpeta
		// Configurar el nombre de la imagen
		String nombreArchivo = imagenGuardada.getId().toString() + obtenerExtension(archivo);

		// Para crear el path de la imagen y la carpeta dónde se guarda la imagen
		String directorio = rutaGlobal + imagen.getUsuario().getId() + "/" + imagen.getCategoria().getId(); // Ruta dónde se guardas las imagenes																					// se
		File rutaDirectorio = new File(directorio);
		if (!rutaDirectorio.exists()) {
			rutaDirectorio.mkdirs(); // Se crea la carpeta si no existe
		}
		// Setea la url de la imagen que tenemos en la bbdd con la path creada arriba
		imagenGuardada.setUrl(imagen.getUsuario().getId() + "/" + imagen.getCategoria().getId() + "/" + nombreArchivo);

		// Guarda la imagen en la bbdd
		Imagen imagenGuardadaFinal = imagenRepository.save(imagenGuardada);

		Path path = Paths.get(directorio + "/" + nombreArchivo);
		try {
			archivo.transferTo(path.toFile());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagenGuardadaFinal;
	}

	/**
	 * Método para ejecutar la consulta que traerá todas las imagenes del usuario
	 * @param usuarioId
	 * @return lista de imagenes
	 */
	public List<Imagen> obtenerImagenesUsuario(Long usuarioId) {
		return imagenRepository.findByUsuarioId(usuarioId);
	}

	/**
	 * Método para borrar una imagen de la base de datos y de la carpeta contenedora.
	 * Se borrarán también las carpetas si están vacías.
	 * @param imagenId
	 */
	@Transactional
	public void borrarImagen(Long imagenId) {
		// Primero buscar la imagen en la bbdd
		Optional<Imagen> imagenOptional = imagenRepository.findById(imagenId);
		if (imagenOptional.isPresent()) {
			// Borrado de la bbdd
			imagenRepository.deleteById(imagenId);
			// Guardamos la imagen de la bbdd en un objeto Imagen
			Imagen imagen = imagenOptional.get();
			// Sacarmos la ruta dón de encuentra la imagen
			String urlImagen = rutaGlobal + imagen.getUrl();
			Path ruta = Paths.get(urlImagen);
			try {
				Files.deleteIfExists(ruta); // Si el fichero existe lo elimina
			} catch (IOException e) {
				// Maneja el error si no se puede borrar el archivo
				throw new RuntimeException("Hubo un error al borrar el archivo: " + urlImagen, e);
			}
			Path carpetaCategoria = ruta.getParent(); // Accede a la ruta de la carpeta categoria y la guarda
			try {
				borrarCarpetaPadreVacia(carpetaCategoria);
			} catch (IOException e) {
				throw new RuntimeException("Hubo un error al borrar la carpeta categoria", e);
			}
			Path carpetaUsuario = carpetaCategoria.getParent(); // Accede a la ruta de la carpeta usuario y la guarda
			try {
				borrarCarpetaPadreVacia(carpetaUsuario);
			} catch (IOException e) {
				throw new RuntimeException("Hubo un error al borrar la carpeta usuario", e);
			}
		}

	}
	
	/**
	 * Método para ejecutar la consulta que guardará la imagen en formato byte para mostrarla en el front
	 * @param imagenId
	 * @return
	 */
	public ResponseEntity<byte[]> obtenerImagenVer(Long imagenId) {
		Optional<Imagen> imagenOptional = imagenRepository.findById(imagenId);
		if (imagenOptional.isPresent()) {
			Imagen imagen = imagenOptional.get();
			String rutaArchivo = rutaGlobal + imagen.getUrl();
			 try {
		            Path path = Paths.get(rutaArchivo);
		            byte[] contenido = Files.readAllBytes(path);

		            String contentType = Files.probeContentType(path);
		            if (contentType == null) {
		                contentType = "application/octet-stream"; // fallback
		            }

		            return ResponseEntity.ok()
		                    .contentType(MediaType.parseMediaType(contentType))
		                    .body(contenido);

		        } catch (IOException e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		        }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

	/**
	 * Método para listar todas las fotografías
	 * @return
	 */
	public List<Imagen> listarTodasImagenes() {
		return imagenRepository.findAll();
	}
	
	/**
	 * Método para pasar a estado validado una fotografía
	 * @param imagenId
	 * @return
	 */
	public Imagen validarImagen(Long imagenId) {
		Imagen imagen = new Imagen();
		String nuevoEstado = "Validada";
		Optional<Imagen> imagenOptional = imagenRepository.findById(imagenId);
		if (imagenOptional.isPresent()) {
			imagen = imagenOptional.get();
			imagen.setEstadoValidacion(nuevoEstado);	
		}
		return imagenRepository.save(imagen);
	}
	
	/**
	 * Método para pasar a estado rechazada una fotografía
	 * @param imagenId
	 * @return
	 */
	public Imagen rechazarImagen(Long imagenId) {
		Imagen imagen = new Imagen();
		String nuevoEstado = "Rechazada";
		Optional<Imagen> imagenOptional = imagenRepository.findById(imagenId);
		if (imagenOptional.isPresent()) {
			imagen = imagenOptional.get();
			imagen.setEstadoValidacion(nuevoEstado);	
		}
		return imagenRepository.save(imagen);
	}
	
	/**
	 * Metodo para seleccionar una imagen
	 * @param imagenId
	 * @return
	 */
	public Imagen seleccionarImagen(Long imagenId) {
		Imagen imagen = new Imagen();
		Optional<Imagen> imagenOptional = imagenRepository.findById(imagenId);
		if(imagenOptional.isPresent()) {
			imagen = imagenOptional.get();
		}else {
			throw new ImagenNoEncontradaException();
		}
		return imagen;
	}
	
	/**
	 * Método para modificar nombre y/o descripción de una imagen
	 * @param imagenEditar
	 * @return
	 */
	public Imagen actualizarImagen(Imagen imagenEditar) {
		Imagen imagen = new Imagen();
		Optional<Imagen> imagenOptional = imagenRepository.findById(imagenEditar.getId());
		if(imagenOptional.isPresent()) {
			imagen = imagenOptional.get();
			imagen.setNombre(imagenEditar.getNombre());
			imagen.setDescripcion(imagenEditar.getDescripcion());
		}
		try {
			return imagenRepository.save(imagen);
		}catch (Exception e){
			throw new ImagenNoGuardada();
		}
	}
	
	/**
	 * Método para obtener el ranking de las imágenes validadas ordenadas en orden descendente
	 * @return
	 */
	public List<ImagenRanking> obtenerRankingImagenes() {
	    List<Imagen> imagenesValidadas = imagenRepository.findByEstadoValidacion("Validada");
	    // Transforma cada imagen en un DTO con la cantidad de votos
	    List<ImagenRanking> ranking = imagenesValidadas.stream()
	        .map(imagen -> {
	            Long id = imagen.getId();
	            String nombre = imagen.getNombre();
	            String categoria = imagen.getCategoria().getNombre();
	            String autor = imagen.getUsuario().getNombre() + " " + imagen.getUsuario().getApellidos();
	            int votos = votoRepository.countByImagen(imagen);

	            return new ImagenRanking(id, nombre, categoria, autor, votos);
	        })
	        // Ordena por cantidad de votos descendente
	        .sorted(Comparator.comparingInt(ImagenRanking::getVotos).reversed())
	        .collect(Collectors.toList());

	    return ranking;
	}

	
	/**
	 * Método para obtener la extension de la imagen que se quiere cargar
	 * @param archivo
	 * @return
	 */
	private String obtenerExtension(MultipartFile archivo) {
		String nombreOriginalImagen = archivo.getOriginalFilename();
		String extension = "";

		if (nombreOriginalImagen != null && nombreOriginalImagen.contains(".")) {
			extension = nombreOriginalImagen.substring(nombreOriginalImagen.lastIndexOf(".") + 1);
		}
		return "." + extension;
	}
	
	/**
	 * Método para borrar las carpetas padres que estén vacías
	 * @param carpetaPadre
	 * @throws IOException
	 */
	private void borrarCarpetaPadreVacia(Path carpetaPadre) throws IOException {
		// Comprueba si el directorio está vacío y lo borra
		if (carpetaPadre != null && Files.isDirectory(carpetaPadre)) {
			try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(carpetaPadre)) {
				if (!dirStream.iterator().hasNext()) {
					Files.delete(carpetaPadre);
				}
			}
		}
	}
}
