package sociosclub.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

	
}