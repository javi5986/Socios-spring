package sociosclub.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class ReportesController {

	private final String pathReporte ="src/main/resources/reportes/";

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/imprimirListadoSocios", consumes = "application/json")
	public ResponseEntity imprimirListadoSocios(
			@RequestBody List<Socios> listSocios) {

		String nombreReporte = "ListadoSocios";
		
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listSocios);
		
		try {
			generarReportes(nombreReporte,itemsJRBean);
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().build();
		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/imprimirSocio", consumes = "application/json")
	public ResponseEntity imprimirSocio(
			@RequestBody Socios socio) throws JRException, IOException {

		String nombreReporte = "ReporteSocio";
		
		try {
			generarReportes(nombreReporte,socio);
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().build();
	}
	
	private void generarReportes(String nombreReporte, Object dataToPrint ) throws JRException, IOException {
		
		HashMap<String, Object> parameters = new HashMap<>();

		parameters.put("parametros", dataToPrint);

		File file = new File(pathReporte + nombreReporte+".jrxml");
		
		InputStream input = new FileInputStream(file);

		JasperDesign jasperDesing = JRXmlLoader.load(input);

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);

		String filePath = System.getProperty("user.home") + "/Desktop/"+nombreReporte+".pdf";

		File file1 = new File(filePath);

		if (!file1.exists()) {
			file1.createNewFile();

		} else { 
			// nombre dinamico 
			int	i = 1;
			boolean pathOk = true;
			do {

				filePath = System.getProperty("user.home") + "/Desktop/"+nombreReporte+"(" + i + ").pdf";
				file1 = new File(filePath);
				if (!file1.exists()) {

					file1.createNewFile();
					pathOk = false;
				} else {
					i++;
				}
			} while (pathOk);

		}

		OutputStream os = new FileOutputStream(file1);
		os.write(pdfReport);
		os.close();
	}
}
	/*
	 * @PostMapping("/pdf") public void pdf(@ModelAttribute(name = "SOCIOS")
	 * List<Socios> socios, HttpServletResponse response) throws JRException,
	 * IOException {
	 * 
	 * JRBeanCollectionDataSource itemsJRBean = new
	 * JRBeanCollectionDataSource(socios);
	 * 
	 * imprimirReporte(itemsJRBean, null)
	 * 
	 * HashMap<String, Object> parameters = new HashMap<>();
	 * 
	 * parameters.put("CollectionParams", itemsJRBean);
	 * 
	 * InputStream input = new FileInputStream(new
	 * File("src/main/resources/reportes/Reporte.jrxml"));
	 * 
	 * JasperDesign jasperDesing = JRXmlLoader.load(input);
	 * 
	 * JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);
	 * 
	 * JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
	 * parameters, new JREmptyDataSource());
	 * 
	 * byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);// , //
	 * "C:\\Users\\JOM\\Desktop\\Reporte.pdf");
	 * 
	 * String mimeType = "application/pdf";
	 * 
	 * response.setContentType(mimeType);
	 * 
	 * response.setHeader("Content-Disposition",
	 * String.format("attachment; filename=\"%s\"", "Reporte.pdf"));
	 * 
	 * response.setContentLength(pdfReport.length);
	 * 
	 * ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
	 * 
	 * FileCopyUtils.copy(inStream, response.getOutputStream());
	 * 
	 * }
	 */



/*
 * JRBeanCollectionDataSource itemsJRBean = new
 * JRBeanCollectionDataSource(socio);
 * 
 * HashMap<String, Object> parameters = new HashMap<>();
 * 
 * parameters.put("CollectionParams", itemsJRBean);
 * 
 * InputStream input = new FileInputStream(pathReporte+"Reporte.jrxml");
 * 
 * JasperDesign jasperDesing = JRXmlLoader.load(input);
 * 
 * JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);
 * 
 * JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
 * parameters, new JREmptyDataSource());
 * 
 * byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);// , //
 * "C:\\Users\\JOM\\Desktop\\Reporte.pdf");
 * 
 * String filePath = System.getProperty("user.home") + "/Desktop/Reporte.pdf";
 * 
 * File file1 = new File(filePath);
 * 
 * if (!file1.exists()) { file1.createNewFile();
 * 
 * } else { // nombre dinamico int i = 1; boolean pathOk = true; do {
 * 
 * filePath = System.getProperty("user.home") + "/Desktop/Reporte(" + i +
 * ").pdf"; file1 = new File(filePath); if (!file1.exists()) {
 * file1.createNewFile(); pathOk = false; }else { i++; } } while (pathOk);
 * 
 * }
 * 
 * OutputStream os = new FileOutputStream(file1); os.write(pdfReport);
 * os.close();
 */
