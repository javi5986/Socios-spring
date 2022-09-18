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
	

	public void alta(Socios socio) {
		
		this.repository.save(socio);

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

	public List<Socios> findAllByApellido(String palabraBusqueda){
		return this.repository.findAllByApellidoContainingOrderByApellidoAscNombre(palabraBusqueda);
	}
	
	public List<Socios> findAllByNombre(String palabraBusqueda){
		return this.repository.findAllByNombreContainingOrderByApellidoAscNombre(palabraBusqueda);
	}


	public List<Socios> findAllByDocumento(String palabraBusqueda) {
		return this.repository.findAllByNumerodocumentoContainingOrderByApellidoAscNombre(palabraBusqueda);
		
	}


	public List<Socios> findAllByApellido(String palabraBusqueda, Long tipoSocio, Long estadoSocio) {
		return this.repository.findByApellidoContainingAndTitularAndHabilitadoOrderByApellidoAscNombre(palabraBusqueda,tipoSocio,estadoSocio);
	}


	public List<Socios> findAllByNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio) {
		return this.repository.findByNombreContainingAndTitularAndHabilitadoOrderByApellidoAscNombre(palabraBusqueda,tipoSocio,estadoSocio);
	}


	public List<Socios> findAllByDocumento(String palabraBusqueda, Long tipoSocio, Long estadoSocio) {
		return this.repository.findByNumerodocumentoContainingAndTitularAndHabilitadoOrderByApellidoAscNombre(palabraBusqueda,tipoSocio,estadoSocio);
	}


	public List<Socios> findAllByApellidoAndEstado(String palabraBusqueda, Long estadoSocio) {
		return this.repository.findByApellidoContainingAndHabilitadoOrderByApellidoAscNombre(palabraBusqueda,estadoSocio);
	}

	public List<Socios> findAllByNombreAndEstado(String palabraBusqueda, Long estadoSocio) {
		return this.repository.findByNombreContainingAndHabilitadoOrderByApellidoAscNombre(palabraBusqueda,estadoSocio);
	}


	public List<Socios> findAllByDocumentoAndEstado(String palabraBusqueda, Long estadoSocio) {
		return this.repository.findByNumerodocumentoContainingAndHabilitadoOrderByApellidoAscNombre(palabraBusqueda,estadoSocio);
	}


	public List<Socios> findAllByApellidoAndTipoSocio(String palabraBusqueda, Long tipoSocio) {
		return this.repository.findByApellidoContainingAndTitularOrderByApellidoAscNombre(palabraBusqueda,tipoSocio);
	}


	public List<Socios> findAllByNombreAndTipoSocio(String palabraBusqueda, Long tipoSocio) {
		return this.repository.findByNombreContainingAndTitularOrderByApellidoAscNombre(palabraBusqueda,tipoSocio);
	}


	public List<Socios> findAllByDocumentoAndTipoSocio(String palabraBusqueda, Long tipoSocio) {
		return this.repository.findByNumerodocumentoContainingAndTitularOrderByApellidoAscNombre(palabraBusqueda,tipoSocio);
	}
	
	
}