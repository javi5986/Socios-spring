package sociosclub.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sociosclub.domain.Socios;
import sociosclub.enums.EnumVistas;
import sociosclub.service.SociosService;

@Controller
@RequestMapping("/socio")
@SessionAttributes("SOCIOS")
public class SociosController {

	@Autowired
	private SociosService sociosService;

	@GetMapping("/abm")
	public String abm() {
		return EnumVistas.ABM.getView();
	}

	@GetMapping("/listados")
	public ModelAndView listados() {
		
		// enviamos un List<socio> vacio para que limpie una busqueda anterior
		List<Socios> socios = new ArrayList<>();
		ModelAndView model = new ModelAndView(EnumVistas.LISTADOS.getView());
		model.addObject("SOCIOS", socios);
		return model;
	}

	@PostMapping("/listados") 
	public String listados(
			// parametros de busqueda
			@RequestParam(name="inputText",required = true ) String inputText,
			@RequestParam(name="busquedaPor",required = true ) String busquedaPor,
			@RequestParam(name="titular",required = true ) Long titular,
			@RequestParam(name="habilitado",required = true ) Long habilitado,
			Model model
			) {
			
			switch (busquedaPor) {
			case "apellido":
				model.addAttribute("SOCIOS", this.sociosService.findAllByApellido(inputText,titular,habilitado));
				break;
			case "nombre":
				model.addAttribute("SOCIOS", this.sociosService.findAllByNombre(inputText,titular,habilitado));
				break;	
			case "numerodocumento":
				model.addAttribute("SOCIOS", this.sociosService.findAllByDocumento(inputText,titular,habilitado));
				break;	
			default:
				break;
			}
			
			// enviamos nuevamente los atributos para que mantenga la busqueda realizada
			model.addAttribute("INPUTTEXT", inputText);
			model.addAttribute("BUSQUEDAPOR", busquedaPor);
			model.addAttribute("TITULAR", titular);
			model.addAttribute("HABILITADO", habilitado);
			
			
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
	public String alta(@Valid @ModelAttribute(name = "SOCIO") Socios socio, BindingResult result) {

		if (result.hasErrors()) {
			return EnumVistas.ALTA.getView();
		}

		else {
			this.sociosService.alta(socio);
			return EnumVistas.ABM.getView();
		}
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id", required = true) Long id, Model model) {

		Socios socio = this.sociosService.buscarPorId(id);
		model.addAttribute("SOCIO", socio);
		return EnumVistas.EDIT.getView();
	}

	@PostMapping("/edit")
	public String edit(@Valid @ModelAttribute(name = "SOCIO") Socios socio, BindingResult result) {

		if (result.hasErrors()) {
			return EnumVistas.EDIT.getView();
		}

		else {
			this.sociosService.alta(socio);
			return EnumVistas.ABM.getView();
		}
	}

	@GetMapping("/baja")
	public String baja(@RequestParam(name = "id", required = true) Long id, Model model) {

		this.sociosService.eliminar(id);

		return EnumVistas.ABM.getView();
	}

	@GetMapping("/list")
	public String list(Model model) {

		List<Socios> socios = this.sociosService.buscarTodos();
		model.addAttribute("SOCIOS", socios);
		return EnumVistas.LIST.getView();
	}

}
