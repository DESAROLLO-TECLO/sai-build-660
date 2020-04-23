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

import mx.com.teclo.saicdmx.negocio.service.reportes.InfraccionesporElementoService;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesporElementoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;


@RestController
public class InfraccionesporElementoRestController {
	
	@Autowired 
	private InfraccionesporElementoService infraccionesElemento;
	
	
	@RequestMapping(value = "/consultaInfraccionesporElemento", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<InfraccionesporElementoVO>> consultaInfraccionesElemento(@RequestBody InfraccionesporElementoVO parametrosBusquedaVO)throws NotFoundException{
       List<InfraccionesporElementoVO> InfraccionesEmpleado =  infraccionesElemento.consultaInfraccionesporElemento(parametrosBusquedaVO);
      
       if(InfraccionesEmpleado.isEmpty()){
    	   	throw new NotFoundException("No se encontraron registros");
          }
		return new ResponseEntity<List<InfraccionesporElementoVO>>(InfraccionesEmpleado, HttpStatus.OK);
	}
   
   
   @RequestMapping(value = "/descargarExcelInfraccionesporElemento", method = RequestMethod.GET)
   @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
   public ResponseEntity<byte[]> descargarExcelInfraccionesElemento(@RequestParam (value = "nombre")String nombre){	    	
   	String filename = nombre;
   	ByteArrayOutputStream outputStream = infraccionesElemento.descargaExcelInfraccionesporElemento();
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
