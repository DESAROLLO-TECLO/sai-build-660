package mx.com.teclo.saicdmx.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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

import mx.com.teclo.saicdmx.negocio.service.reportes.ReportesBitacoraService;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ParametrosBusquedaReporteBitacoraVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TcaBitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class ReportesBitacoraRestController {

 	@Autowired 
	private ReportesBitacoraService reportesBitacoraService;
	
	@RequestMapping(value = "/consultaComponentesBitacora", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>>consultaComponentesBitacora() throws NotFoundException{
		List<CatalogoDinamicoVO> listaComponentes=reportesBitacoraService.consultaComponentesBitacora();

		if(listaComponentes.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<CatalogoDinamicoVO>>(listaComponentes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaConceptosBitacora", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>> consultaConceptosBitacoraPorComponenteId(@RequestParam(value = "componenteId" ) 
	Long componenteId) throws NotFoundException{
		
		List<CatalogoDinamicoVO> listaConceptos= reportesBitacoraService.consultaConceptosPorConponenteId(componenteId);
		if(listaConceptos.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<CatalogoDinamicoVO>>(listaConceptos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaBitacoraCambios", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<TcaBitacoraCambiosVO>> consultaBitacoraCambios(@RequestBody ParametrosBusquedaReporteBitacoraVO 
			parametrosBusquedaVO) throws NotFoundException{
			
		List<TcaBitacoraCambiosVO> listaBitacoraCambiosVO=reportesBitacoraService.consultaBitacoraCambios(parametrosBusquedaVO);
	
		if(listaBitacoraCambiosVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<TcaBitacoraCambiosVO>>(listaBitacoraCambiosVO, HttpStatus.OK);
	}
	

	/*vMetodos Reporte BitacoraAnteriorController*/
	@RequestMapping(value = "/DescargarExcelBitacora", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
    public ResponseEntity<byte[]> DescargarExcelBitacora(@RequestParam (value = "nombre")String nombre){
    	    	
    	String filename = nombre;
    	ByteArrayOutputStream outputStream = reportesBitacoraService.DescargarExcelBitacora();
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
    
	
	@RequestMapping(value = "/ListaComponentesBitacoraAnterior", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>> ListaComponentesBitacoraAnterior() throws NotFoundException{		
		List<CatalogoDinamicoVO> listaConceptos=new ArrayList<>();
		listaConceptos=reportesBitacoraService.obtenerListaComponentes();
		if(listaConceptos.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<CatalogoDinamicoVO>>(listaConceptos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/ListaConceptosAnteriores",method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>> ListaConceptosAnterior(@RequestParam(value = "componenteId") 
	int componenteId) throws NotFoundException{
		List<CatalogoDinamicoVO> ListaConceptos = reportesBitacoraService.obtenerListaConceptos(componenteId);
		return new ResponseEntity<List<CatalogoDinamicoVO>> (ListaConceptos,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaBitacoraCambiosAnteriores", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<TcaBitacoraCambiosVO>> consultaBitacoraCambiosAnteriores(@RequestBody ParametrosBusquedaReporteBitacoraVO 
			parametrosBusquedaVO) throws NotFoundException{
		
		List<TcaBitacoraCambiosVO> listaBitacoraCambiosVO=reportesBitacoraService.consultaBitacoraCambiosAnterior(parametrosBusquedaVO);
	
		if(listaBitacoraCambiosVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<TcaBitacoraCambiosVO>>(listaBitacoraCambiosVO, HttpStatus.OK);
	}
	
}
