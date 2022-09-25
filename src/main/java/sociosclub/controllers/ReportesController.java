package sociosclub.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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

@RestController
@SessionAttributes("SOCIOS")
public class ReportesController {

	@PostMapping("/pdf")
	public void generarReporte(
			@ModelAttribute(name = "SOCIOS") List<Socios> socios,
			HttpServletResponse response) throws JRException, IOException {

	
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(socios);

		HashMap<String, Object> parameters = new HashMap<>();

		parameters.put("CollectionParams", itemsJRBean);

		InputStream input = new FileInputStream(new File("src/main/resources/reportes/Listados.jrxml"));

		JasperDesign jasperDesing = JRXmlLoader.load(input);

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);//, "C:\\Users\\JOM\\Desktop\\Reporte.pdf");

		String mimeType =  "application/pdf";
		
        response.setContentType(mimeType);
        
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "Reporte.pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream( pdfReport);

        FileCopyUtils.copy(inStream, response.getOutputStream());
		
	}
}
