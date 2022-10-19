package sociosclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sociosclub.domain.Categorias;

@Repository
/*
 * T es la entidad sobre la cual voy a trabajar(Entity)
 * ID: es el tipo de datos de la id del Entity
 */
public interface SociosCategoriasRepository extends JpaRepository<Categorias, Long>{ //EX DAO
	
	
}