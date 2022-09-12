package sociosclub.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}