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
	
	List<Socios> findByApellidoContainingAndTitularNotLikeAndHabilitadoNotLikeOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio);

	List<Socios> findByNombreContainingAndTitularNotLikeAndHabilitadoNotLikeOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio);

	List<Socios> findByNumerodocumentoContainingAndTitularNotLikeAndHabilitadoNotLikeOrderByApellidoAscNombre(String palabraBusqueda, Long tipoSocio, Long estadoSocio);
	
}