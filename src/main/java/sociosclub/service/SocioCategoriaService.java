package sociosclub.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sociosclub.domain.SocioCategoria;
import sociosclub.repository.SocioCategoriaRepository;

@Service
public class SocioCategoriaService {

	//D.I
	@Autowired
	private SocioCategoriaRepository repository;

	public List<SocioCategoria> buscarTodos() {

		return this.repository.findAll();
	}

	public Collection<? extends SocioCategoria> findByCategoria(Long idCat) {
		return this.repository.findByIdCategoria(idCat);
	}
	
}