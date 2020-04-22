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

import com.mit.prepago.services.BusinessException;

import mx.com.teclo.saicdmx.negocio.service.reportes.InfraccionesAnualesService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.VistaDTO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.infraccionesAnualesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController	
public class InfraccionesAnualesRestController {
	@Autowired
	private InfraccionesAnualesService infraccAnualService;

	
	/*Metodos para infraccion por mensualidad */
	@RequestMapping(value = "/getListaAnios", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<VistaDTO>> GetListaDepositos()throws NotFoundException, BusinessException{
		List<VistaDTO> listaAnios = infraccAnualService.consultaAniosInfraccion(); //ReporteTotales.obtenerListaDepositos();
		
		if (listaAnios.isEmpty()) {
			throw new NotFoundException("No Se Encontraron Años Para Reporte ");
		}
		return  new  ResponseEntity<List<VistaDTO>>(listaAnios, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaInfraccionesAnuales", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<infraccionesAnualesVO>> consultaInfraccionesAnuales(@RequestParam(value = "anio" )long anio)throws NotFoundException, BusinessException{
		List<infraccionesAnualesVO> listaAnios = infraccAnualService.consultaInfraccionesAnuales(anio);
		if (listaAnios.isEmpty()) {
			throw new NotFoundException("No se encontraron registros ");
		}
		return new  ResponseEntity<List<infraccionesAnualesVO>>(listaAnios, HttpStatus.OK);
	}
	
	/*Descargar Excel infraccionesAnuales*/
	@RequestMapping(value = "/DescargarExcelInfraccionesAnuales", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
   public ResponseEntity<byte[]> descargarExcelInfraccionesAnuales(@RequestParam (value = "nombre")String nombre){
   	    	
   	String filename = nombre;
   	ByteArrayOutputStream outputStream = infraccAnualService.descargaExcelInfraccionesAnuales();
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
   /*Metodos Para infraccciones Anuales Total Anuales parte dos del reporte anua l*/
   @RequestMapping(value = "/consultaInfraccionesTotalAnual", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<infraccionesAnualesVO>> consultaInfraccionesTotalAnuales(@RequestParam(value = "anio" )long anio)throws NotFoundException, BusinessException{
		List<infraccionesAnualesVO> listaAnios = infraccAnualService.consultaInfrraccionesTotalAnual(anio);
		if (listaAnios.isEmpty()) {
			throw new NotFoundException("No se encontraron registros ");
		}
		return new  ResponseEntity<List<infraccionesAnualesVO>>(listaAnios, HttpStatus.OK);
	}
   
}
