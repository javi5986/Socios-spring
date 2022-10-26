package sociosclub.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sociosclub.domain.Categorias;
import sociosclub.domain.Cuotas;
import sociosclub.domain.SocioCategoria;
import sociosclub.domain.Socios;
import sociosclub.service.CategoriasService;
import sociosclub.service.CuotasService;
import sociosclub.service.SocioCategoriaService;
import sociosclub.service.SociosService;

@Controller
@RequestMapping("/cuotas")
public class CuotasController {

	@Autowired
	private CategoriasService categoriasService;

	@Autowired
	private SociosService socioService;

	@Autowired
	private SocioCategoriaService socioCategoriaService;

	@Autowired
	private CuotasService cuotasService;

	@GetMapping("/generarPeriodo")
	public ModelAndView generarPeriodo() {

		Socios socio = new Socios();
		Set<Categorias> categorias = socio.getCategorias();
		Cuotas cuotas = new Cuotas();
		ModelAndView model = new ModelAndView("/cuotas/generarPeriodo");
		model.addObject("SOCIO", socio);
		model.addObject("CATEGORIAS", categorias);
		model.addObject("CUOTAS", cuotas);
		return model;
	}

	@PostMapping("/generarPeriodo")
	public String generarPeriodo(
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "apellido", required = false) String apellido,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "numerodocumento", required = false) String numeroDocumento,
			Model model) {

		model.addAttribute("SOCIO", new Socios());
		if (id != null) {
			Socios socio = this.socioService.buscarPorId(id);
			if (socio == null) {
				model.addAttribute("NOENCONTRADO", true);
			} else {
				List<Cuotas> cuotas = this.cuotasService.findByIdSocio(socio.getId());
				model.addAttribute("SOCIO", socio);
				model.addAttribute("CATEGORIAS", socio.getCategorias());
				model.addAttribute("CUOTAS", cuotas);
			}
		}
		if(apellido!=null && !apellido.isBlank() && !apellido.isEmpty()) {
			
			List<Socios> socio = this.socioService.buscarPorApellido(apellido);
			if(socio.isEmpty()) {
				model.addAttribute("NOENCONTRADO", true);
			}else {
				model.addAttribute("SOCIOS", socio);
				model.addAttribute("MODAL", true);
			}
		}
		if(nombre!=null && !nombre.isBlank() && !nombre.isEmpty()) {
			List<Socios> socio = this.socioService.buscarPorNombre(nombre);
			if(socio.isEmpty()) {
				model.addAttribute("NOENCONTRADO", true);
			}else {
				model.addAttribute("SOCIOS", socio);
				model.addAttribute("MODAL", true);
			}
		}
		if(numeroDocumento!=null && !numeroDocumento.isBlank() && !numeroDocumento.isEmpty()) {
			List<Socios> socio = this.socioService.buscarPorDocumento(numeroDocumento);
			if(socio.isEmpty()) {
				model.addAttribute("NOENCONTRADO", true);
			}else {
				model.addAttribute("SOCIOS", socio);
				model.addAttribute("MODAL", true);
			}
		}
		

		model.addAttribute("id", id);
		model.addAttribute("apellido", apellido);
		model.addAttribute("nombre", nombre);
		model.addAttribute("numerodocumento", numeroDocumento);
		// List<SocioCategoria> socioCategoria =
		// this.socioCategoriaService.buscarTodos();
		// SocioCategoria socioCat = socioCategoria.get(0);
		// Cuotas cuota = new
		// Cuotas(null,socioCat.getIdSocio(),socioCat.getIdCategoria(), new Date(),500l,
		// new Date());
		// this.cuotasService.save(cuota);

		return "/cuotas/generarPeriodo";
	}

}
