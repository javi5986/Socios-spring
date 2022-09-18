package sociosclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sociosclub.domain.Socios;

@Repository
/*
 * T es la entidad sobre la cual voy a trabajar(Entity)
 * ID: es el tipo de datos de la id del Entity
 */
public interface SociosRepository extends JpaRepository<Socios, Long>{ //EX DAO
	
	List<Socios> findAllByApellidoContainingOrderByApellidoAscNombre(String palabraBusqueda);
	
	List<Socios> findAllByNombreContainingOrderByApellidoAscNombre(String palabraBusqueda);
	
	List<Socios> findAllByNumerodocumentoContainingOrderByApellidoAscNombre(String palabraBusqueda);

	List<Socios> findByApellidoContainingAndTitularAndHabilitadoOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio);

	List<Socios> findByNombreContainingAndTitularAndHabilitadoOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio);

	List<Socios> findByNumerodocumentoContainingAndTitularAndHabilitadoOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio);

	List<Socios> findByApellidoContainingAndHabilitadoOrderByApellidoAscNombre(String palabraBusqueda, Long estadoSocio);

	List<Socios> findByNombreContainingAndHabilitadoOrderByApellidoAscNombre(String palabraBusqueda, Long estadoSocio);

	List<Socios> findByNumerodocumentoContainingAndHabilitadoOrderByApellidoAscNombre(String palabraBusqueda,	Long estadoSocio);

	List<Socios> findByApellidoContainingAndTitularOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio);

	List<Socios> findByNombreContainingAndTitularOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio);

	List<Socios> findByNumerodocumentoContainingAndTitularOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio);
	// numerodocumento
	

	
	
}