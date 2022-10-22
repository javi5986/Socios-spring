package sociosclub.controllers;

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

import sociosclub.domain.Categorias;
import sociosclub.enums.EnumCategorias;
import sociosclub.service.CategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private CategoriasService categoriasService;
	
	@GetMapping("/index")
	public ModelAndView configuraciones() {

		List<Categorias> categorias = this.categoriasService.findAll();
		ModelAndView model = new ModelAndView(EnumCategorias.INDEX.getView());
		model.addObject("CATEGORIAS", categorias);
		return model;
	}

	@GetMapping("/eliminar")
	public String eliminar(
			@RequestParam(name = "id", required = true) Long id,
			Model model) {

		this.categoriasService.eliminar(id);
		return EnumCategorias.REDIRECT_INDEX.getView();
	}
	
	@GetMapping("/alta")
	public ModelAndView alta() {
	
	Categorias categoria = new Categorias();
	
	ModelAndView model = new ModelAndView(EnumCategorias.ALTA.getView());
	
	model.addObject("CATEGORIA", categoria);
	
	return model;
	}
	
	@PostMapping("/alta")
	public ModelAndView alta(
			@Validated
			@ModelAttribute(name = "CATEGORIA") Categorias categoria,
			BindingResult resul
			) {
		ModelAndView model = new ModelAndView(EnumCategorias.ALTA.getView());
		
		if(resul.hasErrors()) {
			return model;	
		}
		
		try {
			Categorias newCategoria  = this.categoriasService.crear(categoria);
			model.addObject("CREATE", true);
			model.addObject("CATEGORIA",newCategoria);
		}catch (DataIntegrityViolationException e) {
			if(e.getCause() instanceof ConstraintViolationException){
				model.addObject("DUPLICATE", true);					
			}
		}
		return model;
	}
	
	@GetMapping("/editar/{id}") 
	public String editar(
			@PathVariable(name="id",required = true ) Long id,
			Model model
			) {
		
		Categorias categorias = this.categoriasService.findById(id);
		
		model.addAttribute("CATEGORIA",categorias);
		
		return EnumCategorias.EDITAR.getView();//"edit";
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(
			@Validated
			@ModelAttribute(name = "CATEGORIA") Categorias categorias,
			BindingResult resul
			) {
		ModelAndView model = new ModelAndView(EnumCategorias.EDITAR.getView());
		if(resul.hasErrors()) {
			return model;
		}
		
		Categorias newCategoria  = this.categoriasService.crear(categorias);
		
		model.addObject("UPDATE", true);
		model.addObject("CATEGORIA",newCategoria);
		return model;
	}
}
