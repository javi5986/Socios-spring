package sociosclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sociosclub.domain.Categorias;
import sociosclub.repository.CategoriasRepository;

@Service
public class CategoriasService {

	//D.I
	@Autowired
	private CategoriasRepository repository;

	public List<Categorias> findAll() {
		
		return this.repository.findAll();
	}

	public void eliminar(Long id) {
		this.repository.deleteById(id);		
	}

	public Categorias crear(Categorias categoria) {
		
		return this.repository.save(categoria);
		
	}

	public Categorias findById(Long id) {
		
		Optional<Categorias> optional = this.repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
}