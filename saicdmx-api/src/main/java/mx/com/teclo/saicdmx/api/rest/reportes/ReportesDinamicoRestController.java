package mx.com.teclo.saicdmx.api.rest.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.reportes.ReporteDinamicoIService;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.InfraccionesDiariasVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteInfraccionesGralVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.RptInfraccionesEmpleado;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TotalInfraccionesporArticuloVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;


@RestController	
public class ReportesDinamicoRestController {
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private ReporteDinamicoIService rptDinamicoService;
	
	@RequestMapping(value = "/GetListaReportes", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<ReporteDinamicoVO> GetListaReportes()throws NotFoundException{
		Long idPerfil = usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilId();
		
		ReporteDinamicoVO reporte = rptDinamicoService.obtenerListaReportes(idPerfil); 		
		if (reporte == null) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new  ResponseEntity<ReporteDinamicoVO>(reporte, HttpStatus.OK);
	}	
	
 	@RequestMapping(value = "/CatalogosEntradaDeposito", method = RequestMethod.GET)
 	 @PreAuthorize("hasAnyAuthority('REPORTES_CONSULTA')")
 	public ResponseEntity<List<CatalogoDinamicoVO>> CatalogosCausaIngresos(@RequestParam(value = "tipo" ) String tipoClasificacion)throws NotFoundException{
 		List<CatalogoDinamicoVO> catalogo = null;
 		if(tipoClasificacion.equals("Delegaciones")){
 			   catalogo = rptDinamicoService.catalogoDelegaciones();
 		}else if(tipoClasificacion.equals("Articulos")){
 			  catalogo = rptDinamicoService.catalogoArticulosActivos();
 		}
 		
 		if (catalogo.isEmpty()) {
 			throw new NotFoundException("No se encontraron registros");
 		}
 		return new  ResponseEntity<List<CatalogoDinamicoVO>>(catalogo, HttpStatus.OK);
 	}
	

	
	/*Infracciones Diarias */
	@RequestMapping(value = "/ConsultaInfraccionesDiarias", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<InfraccionesDiariasVO>> ConsultaInfraccionesDiarias(@RequestParam (value = "fInicio")String fechaInicio
			                                                                       )throws NotFoundException{
		List<InfraccionesDiariasVO> listaInfraccionesDiarias = rptDinamicoService.InfraccionesDiarias(fechaInicio);
		if(listaInfraccionesDiarias.isEmpty()){
			throw new NotFoundException("No se encontraron registros ");
		}
		
		return new ResponseEntity<List<InfraccionesDiariasVO>>(listaInfraccionesDiarias, HttpStatus.OK);
	}
	
	/*Infracciones Diarias Detalles */
	@RequestMapping(value = "/ConsultaInfraccionesDiariasDetalle", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<InfraccionesDiariasVO>> ConsultaInfraccionesDiariasDetalle(@RequestParam (value = "fInicio")String fechaInicio)throws NotFoundException{
		List<InfraccionesDiariasVO> listaInfraccionesDiarias = rptDinamicoService.InfraccionesDiariasDetalle(fechaInicio);
		
		if(listaInfraccionesDiarias.isEmpty()){
			throw new NotFoundException("No se encontraron registros ");
		}
		return new ResponseEntity<List<InfraccionesDiariasVO>>(listaInfraccionesDiarias, HttpStatus.OK);
	}
	
	/* Infracciones GENERAL */
	@RequestMapping(value = "/consultaInfraccionesGral", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<ReporteInfraccionesGralVO>> consultaInfraccionesGral(@RequestParam (value = "fInicio")String fechaInicio,
			                                                                    @RequestParam (value = "fFin")String fechaFin)throws NotFoundException{
		
		List<ReporteInfraccionesGralVO> listaInfraccionesGral = rptDinamicoService.consultaInfraccionesGral(fechaInicio, fechaFin);
		if(listaInfraccionesGral.isEmpty()){
			throw new NotFoundException("No se encontraron registros ");
		}
		return new ResponseEntity<List<ReporteInfraccionesGralVO>>(listaInfraccionesGral, HttpStatus.OK);
	}
	
	/*Crear Excel InfraccionesDiarias*/
	@RequestMapping(value = "/descargarExcelInfraccionesDiarias", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
    public ResponseEntity<byte[]> descargarExcelInfraccionesDiarias(){
    	    	
    	String filename = "InfraccionesDiarias.xls";
    	ByteArrayOutputStream outputStream = rptDinamicoService.descargaExcelDeteccMarcado();
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
   
    /*Reporte Ecvel Infracciones GRAL*/
	@RequestMapping(value = "/descargarExcelInfraccionesGral", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
    public ResponseEntity<byte[]> descargarExcelInfraccionesGral(@RequestParam (value = "nombre")String nombre){
    	    	
    	String filename = nombre;
    	ByteArrayOutputStream outputStream = rptDinamicoService.descargaExcelDeteccMarcado();
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
    
    /* Consulta Infracciones por Articulo */
    @RequestMapping(value = "/consultaInfraccionesArticulo", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<ReporteInfraccionesGralVO>> consultaInfraccionesArticulo(@RequestBody ReporteInfraccionesGralVO parametrosBusquedaVO)throws NotFoundException{
        List<ReporteInfraccionesGralVO> InfraccionesArticulo = rptDinamicoService.consultaInfraccionesArticulo(parametrosBusquedaVO);
        if(InfraccionesArticulo.isEmpty()){
        	throw new NotFoundException("No se encontraron registros");
        }
		return new ResponseEntity<List<ReporteInfraccionesGralVO>>(InfraccionesArticulo, HttpStatus.OK);
    	
	}
    
    /* Consulta Infracciones por Delegaciones */
    @RequestMapping(value = "/consultaInfraccionesDelegaciones", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<ReporteInfraccionesGralVO>> consultaInfraccionesDelegaciones(@RequestBody ReporteInfraccionesGralVO parametrosBusquedaVO)throws NotFoundException{
        List<ReporteInfraccionesGralVO> InfraccionesDelegacion = rptDinamicoService.consultaInfraccionesDelegaciones(parametrosBusquedaVO);
        if (InfraccionesDelegacion.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<ReporteInfraccionesGralVO>>(InfraccionesDelegacion, HttpStatus.OK);
	}
    
    /*Reprote Infracciones por Empleado */
    @RequestMapping(value = "/consultaEmpleados", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<CatalogoDinamicoVO>> ConsultaEmpleados(@RequestParam (value = "PlacasOficial")String PlacasOficial)throws NotFoundException{
    	List<CatalogoDinamicoVO> empleados = rptDinamicoService.consultaEmpleados(PlacasOficial);
		if(empleados.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<CatalogoDinamicoVO>>(empleados, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/consultaInfraccionesEmpleados", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<RptInfraccionesEmpleado>> consultaInfraccionesEmpleados(@RequestBody RptInfraccionesEmpleado parametrosBusquedaVO)throws NotFoundException{
        List<RptInfraccionesEmpleado> InfraccionesEmpleado = rptDinamicoService.consultaInfraccionesEmpleados(parametrosBusquedaVO);
        if(InfraccionesEmpleado.isEmpty()){
        	throw new NotFoundException("No se encontraron registros");
           }
		return new ResponseEntity<List<RptInfraccionesEmpleado>>(InfraccionesEmpleado, HttpStatus.OK);
    	
	}
    
    
    @RequestMapping(value = "/descargarExcelEmpleado", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
    public ResponseEntity<byte[]> descargarExcelEmpleado(@RequestParam (value = "nombre")String nombre){
    	    	
    	String filename = nombre;
    	ByteArrayOutputStream outputStream = rptDinamicoService.descargaExcelInfraccionesEmpleados();
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
	
    /*Consulta Infracciones Total por articulo */
    @RequestMapping(value = "/consultaInfraccionesArticuloTotal", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
	public ResponseEntity<List<TotalInfraccionesporArticuloVO>> consultaInfraccionesArticuloTotal(@RequestBody TotalInfraccionesporArticuloVO parametrosBusquedaVO)throws NotFoundException{
       List<TotalInfraccionesporArticuloVO> InfraccionesArticulo = rptDinamicoService.consultaInfraccionesArticuloDetalle(parametrosBusquedaVO);
       if(InfraccionesArticulo.isEmpty()){
       	throw new NotFoundException("No se encontraron registros");
       }
		return new ResponseEntity<List<TotalInfraccionesporArticuloVO>>(InfraccionesArticulo, HttpStatus.OK);
   	
	}
    
    @RequestMapping(value = "/descargarExcelTotalporArticulo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
    public ResponseEntity<byte[]> descargarExcelTotalporArticulo(@RequestParam (value = "nombre")String nombre){
    	    	
    	String filename = nombre;
    	ByteArrayOutputStream outputStream = rptDinamicoService.TotalInfraccionesporArticuloExcel();
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
