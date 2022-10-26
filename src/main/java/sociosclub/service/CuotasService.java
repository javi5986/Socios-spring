package sociosclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sociosclub.domain.Categorias;
import sociosclub.domain.Cuotas;
import sociosclub.repository.CuotasRepository;

@Service
public class CuotasService {

	//D.I
	@Autowired
	private CuotasRepository repository;

	public List<Cuotas> findAll() {
		
		return this.repository.findAll();
	}

	public Cuotas save(Cuotas cuota) {
		return this.repository.save(cuota);
		
	}

	public List<Cuotas> findByIdSocio(Long idSocio) {
		
		return this.repository.findByIdSocio(idSocio);
	}

	
}