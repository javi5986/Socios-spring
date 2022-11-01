package sociosclub.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sociosclub.domain.Categorias;
import sociosclub.domain.Socios;
import sociosclub.enums.EnumSociosCategorias;
import sociosclub.service.CategoriasService;
import sociosclub.service.SociosService;

@Controller
@RequestMapping("/sociosCategorias")
public class SociosCategoriasController {

	@Autowired
	private CategoriasService categoriasService;
	
	@Autowired
	private SociosService sociosService;
	
	//@Autowired
//	private SocioCategoriaService socioCategoriaService;
	
	@GetMapping("/listados")
	public ModelAndView listados() {
		
		List<Categorias> categorias = this.categoriasService.findAll();
		ModelAndView model = new ModelAndView(EnumSociosCategorias.LISTADOS.getView());
		model.addObject("CATEGORIAS", categorias);
		return model;
	}
	
	@PostMapping("/listados")
	public String listados(
			@RequestParam(name = "inputText", required = true) String inputText,
			@RequestParam(name = "busquedaPor", required = true) String busquedaPor,
			@RequestParam(name = "catSeleccionadas", required = true) Long[] idCategorias,
			Model model
			) {
		// listas para enviar
		Set<Socios> sociosToSend = new HashSet<>();
		// realizamos la busqueda por los parametros ingresados
		// solo socios habilitados
		List<Socios> sociosDB = buscarPorParametros(inputText, busquedaPor, -1l, 1l);
		
		for (Socios socios : sociosDB) {

			Set<Categorias> categorias = socios.getCategorias();
			
			Set<Categorias> categoriasTemp = new HashSet<>();
			
			for (Categorias cat : categorias) {
				
				
				// comparamos las cat del socios con las ingresadas en front
				for(Long idCat:idCategorias) {
					
					if(cat.getId()==idCat) {
						categoriasTemp.add(cat);
						//sociosToSend.add(socios);
					}
				}
			}
			if(!categoriasTemp.isEmpty()) {
				socios.setCategorias(categoriasTemp);
				sociosToSend.add(socios);
			}
		}
		
				
		List<Categorias> categorias = this.categoriasService.findAll();
		model.addAttribute("CATEGORIAS", categorias);
		model.addAttribute("INPUTTEXT", inputText);
		model.addAttribute("BUSQUEDAPOR", busquedaPor);
		model.addAttribute("CHECKSELECCIONADOS", idCategorias);
		
		if(sociosToSend.isEmpty()) {
			model.addAttribute("NOENCONTRADO", true);
			return EnumSociosCategorias.LISTADOS.getView();
		}
		model.addAttribute("SOCIOS", sociosToSend);
		return EnumSociosCategorias.LISTADOS.getView();
	}
	
	private List<Socios> buscarPorParametros(String inputText, String busquedaPor, long l, long m) {
		List<Socios> socios = new ArrayList<>();
		switch (busquedaPor) {
		case "apellido":
			socios = this.sociosService.findAllByApellido(inputText, -1l, 0l);
			break;
		case "nombre":
			socios = this.sociosService.findAllByNombre(inputText, -1l, 0l);
			break;
		case "numerodocumento":
			socios = this.sociosService.findAllByDocumento(inputText, -1l, 0l);
			break;
		default:
			break;
		}
		return socios;
	}

	@GetMapping("/buscarUsuario")
	public String buscarUsuario() {
	return EnumSociosCategorias.BUSCARUSUARIO.getView();
	}

	@PostMapping("/buscarUsuario")
	public String buscarUsuario(
			@RequestParam(name = "inputText", required = true) String inputText,
			@RequestParam(name = "busquedaPor", required = true) String busquedaPor,
			@RequestParam(name = "titular", required = true) Long titular,
			@RequestParam(name = "habilitado", required = true) Long habilitado,
			Model model
			) {
		
		busquedaPorParametros(inputText, busquedaPor, titular, habilitado, model);
		
		return EnumSociosCategorias.BUSCARUSUARIO.getView();
	}
	
	@GetMapping("/seleccionarCategoria/{id}") 
	public String seleccionarCategoria(
			@PathVariable(name="id",required = true ) Long id,
			Model model
			) {
		
		Socios socio = this.sociosService.buscarPorId(id);
		//Set<Categorias> categoriaSocio = socio.getCategorias();
		List<Categorias> categorias = this.categoriasService.findAll();
		
		model.addAttribute("CATEGORIAS",categorias);
		model.addAttribute("SOCIO", socio);
		//model.addAttribute("CATEGORIASSOCIO", categoriaSocio);
		
		return EnumSociosCategorias.SELECCIONARCATEGORIA.getView();
	}
	
	public void busquedaPorParametros(String inputText, String busquedaPor, Long titular, Long habilitado,
			Model model) {
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
		model.addAttribute("INPUTTEXT", inputText);
		model.addAttribute("BUSQUEDAPOR", busquedaPor);
		model.addAttribute("TITULAR", titular);
		model.addAttribute("HABILITADO", habilitado);
	}
	
	@GetMapping("/eliminarCategoria/Socio={idSocio}&Categoria={idCategoria}") 
	public String eliminarCategoria(
			@PathVariable(name="idSocio",required = true ) Long idSocio,
			@PathVariable(name="idCategoria",required = true ) Long idCategoria,
			Model model
			) {
		
		Socios socio = this.sociosService.buscarPorId(idSocio);
		Categorias categoria = this.categoriasService.findById(idCategoria);
		Set<Categorias> categoriasSocio = socio.getCategorias();
		Iterator<Categorias> it = categoriasSocio.iterator();
		while(it.hasNext()) {
			if(it.next().equals(categoria)) {
				it.remove();
			}
		}
		this.sociosService.alta(socio); // ver
		return EnumSociosCategorias.REDIRECT_SELECCIONARCATEGORIA.getView()+"/"+idSocio;
	}
		
	@GetMapping("/agregarCategoria/Socio={idSocio}&Categoria={idCategoria}") 
	public String agregarCategoria(
			@PathVariable(name="idSocio",required = true ) Long idSocio,
			@PathVariable(name="idCategoria",required = true ) Long idCategoria,
			Model model
			) {
		
		Socios socio = this.sociosService.buscarPorId(idSocio);
		Categorias categoria = this.categoriasService.findById(idCategoria);		
		Set<Categorias> categoriasSocio = socio.getCategorias();
		categoriasSocio.add(categoria);
		this.sociosService.alta(socio);
		return EnumSociosCategorias.REDIRECT_SELECCIONARCATEGORIA.getView()+"/"+idSocio;
	}
}
