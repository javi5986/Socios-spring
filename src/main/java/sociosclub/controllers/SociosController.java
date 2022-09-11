package sociosclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
			
}