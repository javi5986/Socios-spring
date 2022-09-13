package sociosclub.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sociosclub.domain.Socios;
import sociosclub.enums.EnumVistas;
import sociosclub.service.SociosService;

@Controller
@RequestMapping("/socio")
public class SociosController {

	@Autowired
	private SociosService sociosService;

	@GetMapping("/abm") 
	public String abm() {
		return EnumVistas.ABM.getView();
	}
	
		
	@GetMapping("/listados") 
	public String listados() {
		return EnumVistas.LISTADOS.getView();
	}
	
	@PostMapping("/listados") 
	public String listados1(
			@RequestParam(name="palabraBusqueda",required = true ) String palabraBusqueda,
			@RequestParam(name="parametro",required = true ) String parametro,
			@RequestParam(name="tipoSocio",required = true ) String tipoSocio,
			@RequestParam(name="estadoSocio",required = true ) String estadoSocio,
			Model model
			) {
		
	
		model.addAttribute("SOCIOS", this.sociosService.buscarTodos());
		
		return EnumVistas.LISTADOS.getView();
	}
	
	@GetMapping("/alta")
	public ModelAndView alta() {
		Socios socio = new Socios();
		
		ModelAndView model = new ModelAndView(EnumVistas.ALTA.getView());
		model.addObject("SOCIO", socio);
		
		return model;
	}
	
	
	@PostMapping("/alta")
	public String alta(
		@Valid	
		@ModelAttribute(name="SOCIO") Socios socio,
		BindingResult result
		){
		
			if(result.hasErrors()) {
				return EnumVistas.ALTA.getView();
			}
			
			else{
				this.sociosService.alta(socio);
				return EnumVistas.ABM.getView();
			}	
		}
    
    @RequestMapping("/edit/{id}") 
	public String edit(
			@PathVariable(name="id", required=true) Long id,
			Model model
			) {
	
		Socios socio = this.sociosService.buscarPorId(id);	
		model.addAttribute("SOCIO", socio);
		return EnumVistas.EDIT.getView();
	}
	
	
	@PostMapping("/edit")
	public String edit(
		@Valid	
		@ModelAttribute(name="SOCIO") Socios socio,
		BindingResult result
		){
		
			if(result.hasErrors()) {
				return EnumVistas.EDIT.getView();
			}
			
			else{
				this.sociosService.alta(socio);
				return EnumVistas.ABM.getView();
			}	
		}
	
	@GetMapping("/baja")	
	public String baja(
			@RequestParam(name="id", required = true) Long id,
			Model model
			) {
	
		this.sociosService.eliminar(id);
		
		return EnumVistas.ABM.getView();
}

@GetMapping("/list") 
	public String list(
			Model model
			) {
		
		List<Socios> socios= this.sociosService.buscarTodos(); 
		model.addAttribute("SOCIOS", socios);
		return EnumVistas.LIST.getView();
	}
    
}
