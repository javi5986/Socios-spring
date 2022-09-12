package sociosclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String alta() {
		return EnumVistas.ALTA.getView();
	}
		
	@GetMapping("/listados") 
	public String listados() {
		return EnumVistas.LISTADOS.getView();
	}
	
	@PostMapping("/listados/{busqueda}") 
	public String listados1(
			@PathVariable(name="busqueda",required = true ) String busqueda,			
			Model model) {
		Integer a = 1;
		return "socios";
	}
}