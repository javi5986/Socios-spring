package sociosclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sociosclub.domain.Cuotas;

@Repository
/*
 * T es la entidad sobre la cual voy a trabajar(Entity)
 * ID: es el tipo de datos de la id del Entity
 */
public interface CuotasRepository extends JpaRepository<Cuotas, Long>{

	List<Cuotas> findAll();

	List<Cuotas> findByIdSocio(Long idSocio);
}