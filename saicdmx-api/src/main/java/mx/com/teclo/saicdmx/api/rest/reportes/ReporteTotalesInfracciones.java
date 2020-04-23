package mx.com.teclo.saicdmx.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

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

import mx.com.teclo.saicdmx.negocio.service.reportes.ReporteTotalInfraccionesI;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.rptInfraccionesMensuales;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController	
public class ReporteTotalesInfracciones {
	
	@Autowired
	private ReporteTotalInfraccionesI ReporteTotales;
	
	
	/*Metodos para infraccion por mensualidad */
	@RequestMapping(value = "/GetListaDepositos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>> GetListaDepositos()throws NotFoundException{
		List<CatalogoDinamicoVO> listaDepositos = ReporteTotales.obtenerListaDepositos();
		if (listaDepositos.isEmpty()) {
			throw new NotFoundException("No se encontraron Depositos ");
		}
		return new  ResponseEntity<List<CatalogoDinamicoVO>>(listaDepositos, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetListaAnios", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>> GetListaAnios()throws NotFoundException{
		List<CatalogoDinamicoVO> listaAnios = ReporteTotales.obtenerListaAnios();
		if (listaAnios.isEmpty()) {
			throw new NotFoundException("No se encontraron Años ");
		}
		return new  ResponseEntity<List<CatalogoDinamicoVO>>(listaAnios, HttpStatus.OK);
	}
	
	  @RequestMapping(value = "/consultaInfraccionesMensuales", method = RequestMethod.POST)
	  @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
		public ResponseEntity<List<rptInfraccionesMensuales>> consultaInfraccionesMensuales(@RequestBody rptInfraccionesMensuales parametrosBusquedaVO)throws NotFoundException{
	        List<rptInfraccionesMensuales> InfraccionesMensuales = ReporteTotales.obtenerInfraccionesMensuales(parametrosBusquedaVO);
	        if(InfraccionesMensuales.isEmpty()){
	        	throw new NotFoundException("No se encontraron registros");
	           }
			return new ResponseEntity<List<rptInfraccionesMensuales>>(InfraccionesMensuales, HttpStatus.OK);	
		}
	  
	  @RequestMapping(value = "/DescargarExcelTotalInfracMensuales", method = RequestMethod.GET)
	  @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	    public ResponseEntity<byte[]> DescargarExcelTotalInfracMensuales(@RequestParam (value = "nombre")String nombre){
	    	    	
	    	String filename = nombre;
	    	ByteArrayOutputStream outputStream = ReporteTotales.descargaExcelInfracMensuales();
	    	final byte[] bytes = outputStream.toByteArray();
	    	
	    	HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
	    	headers.add("Content-Disposition", "attachment; filename=" + filename);
	    	headers.add("filename",   filename);
	        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	        headers.setContentLength(bytes.length);
	        
	        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	        
	        return response;
		}
	
}
