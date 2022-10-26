package sociosclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sociosclub.domain.Socios;
import sociosclub.repository.SociosRepository;

@Service
public class SociosService {

	//D.I
	@Autowired
	private SociosRepository repository;

	public List<Socios> buscarTodos() {

		return this.repository.findAll();
	}
	

	public Socios alta(Socios socio) {
		
			return this.repository.save(socio);

	}


	public Socios buscarPorId(Long id) {
		Optional<Socios> socios = this.repository.findById(id);
		
		if(socios.isPresent()) {
			return socios.get();
		} else {
			return null;
		}
	}


	public void eliminar(Long id) {
		this.repository.deleteById(id);
		
	}


	public List<Socios> findAllByApellido(String palabraBusqueda, Long tipoSocio, Long estadoSocio) {
		return this.repository.findByApellidoContainingAndTitularNotLikeAndHabilitadoNotLikeOrderByApellidoAscNombre(palabraBusqueda,tipoSocio,estadoSocio);
	}


	public List<Socios> findAllByNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio) {
		return this.repository.findByNombreContainingAndTitularNotLikeAndHabilitadoNotLikeOrderByApellidoAscNombre(palabraBusqueda,tipoSocio,estadoSocio);
	}


	public List<Socios> findAllByDocumento(String palabraBusqueda, Long tipoSocio, Long estadoSocio) {
		return this.repository.findByNumerodocumentoContainingAndTitularNotLikeAndHabilitadoNotLikeOrderByApellidoAscNombre(palabraBusqueda,tipoSocio,estadoSocio);
	}


	public List<Socios> buscarPorApellido(String apellido) {
		return this.repository.findByApellidoContaining(apellido);
	}


	public List<Socios> buscarPorNombre(String nombre) {
		return this.repository.findByNombreContaining(nombre);
	}


	public List<Socios> buscarPorDocumento(String numeroDocumento) {
		return this.repository.findByNumerodocumentoContaining(numeroDocumento);
	}
	
}