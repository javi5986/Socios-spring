package sociosclub.reportes;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sociosclub.domain.Socios;
import sociosclub.enums.EnumVistas;

@Controller
@RequestMapping("/reportes")
@SessionAttributes("SOCIOS")
public class Reportes {
	
	@PostMapping("/pdf")
	public ModelAndView generarReporte( 
			@ModelAttribute(name="SOCIOS") List<Socios> socios) {
		
		//JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(socio);
		
		ModelAndView model = new ModelAndView(EnumVistas.LISTADOS.getView());
		model.addObject(socios);
		return model;
	}

}
