package mx.com.teclo.saicdmx.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.reportes.ReporteService;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.FormatoDescargaVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ObjectGenericVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReportesTaqVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController	
@RequestMapping("/consulta")
public class DinamicRestController {
	
	@Autowired
	private ReporteService reporteService;
	
	@RequestMapping(value = "/reporte", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('GET_REPORTES_DINAMICOS')")
	public ResponseEntity<ObjectGenericVO> consultaDinamica(@RequestBody ObjectGenericVO params) throws NotFoundException, SQLException, NamingException{
		/**
		 * Comprueba si existe el reporte
		 */
		ReportesTaqVO reporteExiste = reporteService.compruebaSiExisteReporteById(params.getParametros());
		if (reporteExiste == null)
			throw new NotFoundException("Es posible que el reporte no exista en el sistema");
		/**
		 * Si el reporte es encontrado se ejecuta el mybatis
		 */
		ObjectGenericVO objectReturn = reporteService.executeQuery(params.getParametros(), reporteExiste);
		if ((objectReturn.getParametros() != null) && objectReturn.getValues() == null && objectReturn.getCabeceras() == null)
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<ObjectGenericVO>(objectReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('GET_DESCARGA_REPORTE_DINAMICO')")
	public ResponseEntity<byte[]> descargaExcel(@RequestBody ObjectGenericVO params) throws NotFoundException, IOException, SQLException, NamingException, BusinessException{
		/**
		 * Comprueba si existe el reporte
		 */
		ReportesTaqVO reporteExiste = reporteService.compruebaSiExisteReporteById(params.getParametros());
		if (reporteExiste == null)
			throw new NotFoundException("Es posible que el reporte no exista en el sistema");
		/**
		 * Si el reporte es encontrado se ejecuta el mybatis
		 */
		ObjectGenericVO objectReturn = reporteService.executeQuery(params.getParametros(), reporteExiste);
		if ((objectReturn.getParametros() != null) && objectReturn.getValues() == null && objectReturn.getCabeceras() == null)
			throw new NotFoundException("No se encontraron registros");

		ByteArrayOutputStream reporteObject = reporteService.generateExcel(objectReturn, reporteExiste);
		final byte[] bytes = reporteObject.toByteArray();

		FormatoDescargaVO formatoDescarga = reporteExiste.getReporteFormato().get(0).getFormatoDescarga();
		String txExtension = formatoDescarga == null ? ".xlsx" :  formatoDescarga.getCdExtension();
		 
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.add("Content-Disposition", "attachment; filename=" + reporteExiste.getNbReporte() + txExtension);
		headers.add("filename", reporteExiste.getNbReporte() + txExtension);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaPrevia", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GET_DESCARGA_REPORTE_DINAMICO')")
	public ResponseEntity<List<LinkedHashMap<Object, Object>>> consultaPrevia (@RequestParam(name="idParametro") Long idParametro,
			                                                                   @RequestParam(name="valores")String valores) throws SQLException, NamingException, NotFoundException{
		
		List<LinkedHashMap<Object,Object>> mapReturn = reporteService.executeSubQuery(idParametro, valores);
		/*si tiene una sub consulta*/
		if (mapReturn.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<LinkedHashMap<Object, Object>>>(mapReturn, HttpStatus.OK);
	}
}
