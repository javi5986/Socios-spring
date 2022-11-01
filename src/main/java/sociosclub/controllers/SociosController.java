package sociosclub.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sociosclub.domain.Socios;
import sociosclub.enums.EnumSocios;
import sociosclub.service.SociosService;

@Controller
@RequestMapping("/socio")
public class SociosController {

	@Autowired
	private SociosService sociosService;

	@GetMapping("/listados")
	public ModelAndView listados() {

		// enviamos un List<socio> vacio para que limpie una busqueda anterior
		List<Socios> socios = new ArrayList<>();
		ModelAndView model = new ModelAndView(EnumSocios.LISTADOS.getView());
		model.addObject("SOCIOS", socios);
		//model.addObject("SUCCESS", false);
		return model;
	}

	@PostMapping("/listados")
	public String listados(
			// parametros de busqueda
			@RequestParam(name = "inputText", required = true) String inputText,
			@RequestParam(name = "busquedaPor", required = true) String busquedaPor,
			@RequestParam(name = "titular", required = true) Long titular,
			@RequestParam(name = "habilitado", required = true) Long habilitado, Model model) {

		busquedaPorParametros(inputText, busquedaPor, titular, habilitado, model);

		return EnumSocios.LISTADOS.getView();
	}

	@GetMapping("/alta")
	public ModelAndView alta() {
		Socios socio = new Socios();

		ModelAndView model = new ModelAndView(EnumSocios.ALTA.getView());
		model.addObject("SOCIO", socio);
		return model;
	}

	@PostMapping("/alta")
	public ModelAndView alta(
			@Validated 
			@ModelAttribute(name = "SOCIO") Socios socio,
			BindingResult result) {

		ModelAndView model = new ModelAndView(EnumSocios.ALTA.getView());
		Socios socioNew = null;
		if (!result.hasErrors()) {
			try {
				 socioNew = this.sociosService.alta(socio);
				 model.addObject("SOCIO", socioNew);
				 model.addObject("SUCCESS", true);
			}catch (DataIntegrityViolationException e) {
				if(e.getCause() instanceof ConstraintViolationException){
					model.addObject("DUPLICATE", true);					
				}
			}
		}
		return model;

	}

	@GetMapping("/buscar")
	public ModelAndView buscar() {
		List<Socios> socios = new ArrayList<>();
		ModelAndView model = new ModelAndView(EnumSocios.BUSCARSOCIO.getView());
		model.addObject("SOCIOS", socios);
		return model;
	}

	@PostMapping("/buscar")
	public String buscar(
			// parametros de busqueda
			@RequestParam(name = "inputText",required = false) String inputText,
			@RequestParam(name = "busquedaPor",required = false) String busquedaPor,
			@RequestParam(name = "titular",required = false) Long titular,
			@RequestParam(name = "habilitado",required = false) Long habilitado,
			Model model) {
		
		busquedaPorParametros(inputText, busquedaPor, titular, habilitado, model);
		
		return EnumSocios.BUSCARSOCIO.getView();
	}
	
	@RequestMapping("/editar/{id}")
	public String editar(
			@PathVariable(name = "id", required = true) Long id,
			Model model){
		
		Socios socio = this.sociosService.buscarPorId(id);
		model.addAttribute("SOCIO", socio);
		return EnumSocios.EDITAR.getView();
	}


	@PostMapping("/editar")
	public ModelAndView editar(
			@Validated 
			@ModelAttribute(name = "SOCIO") Socios socio,
			BindingResult result
			) {
		ModelAndView model = new ModelAndView(EnumSocios.EDITAR.getView());
		if (result.hasErrors()) {
			return model;
		}
		else {
			if(socio.getHabilitado()==0) {
				socio.setCategorias(null);
			}
			this.sociosService.alta(socio);
			model.addObject("UPDATE", true);
		}
		return model;
	}

	@GetMapping("/baja")
	public String baja(
			@RequestParam(name = "id", required = true) Long id,
			Model model) {

		this.sociosService.eliminar(id);
		return EnumSocios.ALTA.getView();
	}

	public void busquedaPorParametros(String inputText, String busquedaPor, Long titular, Long habilitado,
			Model model) {
	/*	if(inputText==null){
			String inputTextModel = (String) model.getAttribute("INPUTTEXT");
			String busquedaPorModel = (String) model.getAttribute("BUSQUEDAPOR");
			Long titularModel = (Long) model.getAttribute("TITULAR");
			Long habilitadoModel = (Long) model.getAttribute("HABILITADO");
			busquedaPorParametros(inputTextModel, busquedaPorModel, titularModel, habilitadoModel, model);
		}else {*/
			switch (busquedaPor) {
			case "apellido":
				model.addAttribute("SOCIOS", this.sociosService.findAllByApellido(inputText, titular, habilitado));
				break;
			case "nombre":
				model.addAttribute("SOCIOS", this.sociosService.findAllByNombre(inputText, titular, habilitado));
				break;
			case "numerodocumento":
				model.addAttribute("SOCIOS", this.sociosService.findAllByDocumento(inputText, titular, habilitado));
				break;
			default:
				break;
			}
			/*
			model.addAttribute("INPUTTEXT", inputText);
			model.addAttribute("BUSQUEDAPOR", busquedaPor);
			model.addAttribute("TITULAR", titular);
			model.addAttribute("HABILITADO", habilitado);
			}*/
	}
	
}