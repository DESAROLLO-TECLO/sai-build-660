package mx.com.teclo.saicdmx.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.reportes.TotalInfraccionesMensualesService;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesMensualesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;


@RestController	
public class TotalInfraccionesMensualesRestController {
	
	@Autowired 
	private TotalInfraccionesMensualesService consultaTotalMensuales;
	
	@RequestMapping(value = "/consultaTotalInfraccMensuales", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<TotalInfraccionesMensualesVO>> consultaInfraccionesMensuales(@RequestParam (value = "fechaInicio")String fechaInicio,
			                                                                                @RequestParam (value = "fechaFin")String fechaFin)throws NotFoundException{
		List<TotalInfraccionesMensualesVO> infraccionesMensuales = consultaTotalMensuales.consultaInfracciones(fechaInicio, fechaFin);
		if (infraccionesMensuales.isEmpty()) {
			throw new NotFoundException("No se encontraron Infracciones");
		}
		return new  ResponseEntity<List<TotalInfraccionesMensualesVO>>(infraccionesMensuales, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/descargarExcelInfraccionesMensualesTotal", method = RequestMethod.GET)
	  @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	    public ResponseEntity<byte[]> DescargarExcelTotalInfracMensuales(@RequestParam (value = "nombre")String nombre){
	    	    	
	    	String filename = nombre;
	    	ByteArrayOutputStream outputStream = consultaTotalMensuales.descargaExcelInfracMensuales();
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
