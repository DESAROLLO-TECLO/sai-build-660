package mx.com.teclo.saicdmx.api.rest.lineadecaptura;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
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
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.estadistica.TipoInfraccionService;
import mx.com.teclo.saicdmx.negocio.service.infracciones.InfraccionService;
import mx.com.teclo.saicdmx.negocio.service.lineadecaptura.ConsultaLCService;
import mx.com.teclo.saicdmx.negocio.service.lineadecaptura.ReasignacionLCService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionRadarDTO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionEstadisticaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesOficialVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

/***
 * 
 * @author Jesus Gutierrez
 *
 */

@RestController
public class LineaCapturaRestController {

	@Autowired 
	private ReasignacionLCService reasignacionLCService;
	
	@Autowired
	private ConsultaLCService consultaLCService;
	
	@Autowired
	private InfraccionService infraccionesService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private TipoInfraccionService tipoInfraccionService;
	
	@Autowired
	ServletContext context;
	
	/*COMIENZA REASIGNACION*/
	@RequestMapping(value = "/buscarInfraccionRadarByFolio", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCIONRADAR_BY_FOLIO')")
	public ResponseEntity<ConsultaInfraccionForReasignacionVO> buscarInfraccionRadarByFolio(@RequestParam("folio") String folio, @RequestParam("fechareasignacion") String fechareasignacion) throws NotFoundException, ParseException{
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		String isFolioRadar = tipoInfraccionService.getVistaPorTipo(folio);
		isFolioRadar = isFolioRadar != null? isFolioRadar.toUpperCase().trim(): "";
		
		if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
			reasignacionLCService.UpdateFechaEmParaLC(folio, fechareasignacion, usuario);
		}
		
		ConsultaInfraccionForReasignacionVO infraccionRadar = reasignacionLCService.buscaInfraccionRadarByFolio(folio,isFolioRadar);
		
		if (infraccionRadar == null) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<ConsultaInfraccionForReasignacionVO>(infraccionRadar, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarDetalleReasignacionesByInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_DETALLE_REASIGNACIONES_BY_INFRACCION')")
	public ResponseEntity<List<DetalleDeReasignacionesInfraccionVO>> buscarDetalleReasignacionesByInfraccion(@RequestParam("folio") String folio){
		List<DetalleDeReasignacionesInfraccionVO> detalleReasignaciones = reasignacionLCService.consultaDetalleReasignacionesByInfraccion(folio, 0);
		return new ResponseEntity<List<DetalleDeReasignacionesInfraccionVO>>(detalleReasignaciones, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/generarReportePagoFinanzas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_PDF_PAGO_FINANZAS')")
	public ResponseEntity<byte[]> generarPDFPagoFinanzas(@RequestParam(value = "reasignacionVO") String reasignacionVO) throws NotFoundException, FileNotFoundException {
		String filename = "reportePagoTesoreria.pdf" ;
		String rutaArchivo = context.getRealPath("/WEB-INF/jasper/radares/imprimePagoTesoreria.jasper");
		String rutaImagen = context.getRealPath("/WEB-INF/imagenes/FUT2016.png");
		

		DetalleDeReasignacionesInfraccionVO detalleReasignacion = crearDetalleDeReasignacionesInfraccionVO(reasignacionVO);
		
		String isFolioRadar = tipoInfraccionService.getVistaPorTipo(detalleReasignacion.getFolioInfraccion());
		isFolioRadar = StringUtils.trimToEmpty(isFolioRadar);
		if(isFolioRadar.equals("V_WS_SF_LINEAC_REASIGNA")){
			InfraccionRadarDTO infraccionRadar = infraccionesService.buscarInfraccionPorFolio(detalleReasignacion.getFolioInfraccion());
			if(infraccionRadar == null){
				throw new NotFoundException("No se encontraron infracciones");
			}
			detalleReasignacion.setPlacaVehiculo(infraccionRadar.getInfraccPlaca());
		}
		else{
			String placa = infraccionesService.getPlacaInfraccion(detalleReasignacion.getFolioInfraccion(), isFolioRadar);
			detalleReasignacion.setPlacaVehiculo(StringUtils.trimToEmpty(placa));
		}
		/*SETEO DE PRUEBA
		detalleReasignacion.setLineaCaptura("123");
		detalleReasignacion.setVigencia("13/10/2016");
		detalleReasignacion.setImporte(new BigDecimal(0.00));
		detalleReasignacion.setDescuento(new BigDecimal(0.00));
		detalleReasignacion.setTotal(new BigDecimal(0.00));
		detalleReasignacion.setRecargos(new BigDecimal(0.00));*/
		
		ByteArrayOutputStream reporte = reasignacionLCService.generaReporteFUTPDF(detalleReasignacion, rutaArchivo, rutaImagen);
		final byte[] bytes = reporte.toByteArray();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename ="+filename);
		headers.add("filename",   filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    headers.setContentLength(bytes.length);
	    
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		
		return response;		
	}
		
	@RequestMapping(value = "/reasignarLineaDeCaptura", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('REASIGNAR_LINEA_DE_CAPTURA')")
	public ResponseEntity<RespuestaWSReasignaLineaCapturaVO> reasignarLineaDeCaptura(@RequestParam("infraccionRadarVO") String infraccionRadarVO, @RequestParam("descuento") Integer descuento) throws ProtocolException, IOException, ParserConfigurationException, SAXException, ParseException {
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		ConsultaInfraccionForReasignacionVO infraccion = crearInfraccionReasignacionVO(infraccionRadarVO);
		
		String isFolioRadar = tipoInfraccionService.getVistaPorTipo(infraccion.getFolio());
		isFolioRadar = isFolioRadar != null? isFolioRadar.toUpperCase().trim(): "";
		
		//infraccion.setFechaEmision(infraccion.getFechaEmision());
		
		RespuestaWSReasignaLineaCapturaVO respuesta = reasignacionLCService.ReasignarLineaDeCaptura(infraccion, descuento, usuario, isFolioRadar);
		return new ResponseEntity<RespuestaWSReasignaLineaCapturaVO>(respuesta, HttpStatus.OK);
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/reasignarLineaDeCaptura/{id}").buildAndExpand(respuesta.getFolio()).toUri());
		return new ResponseEntity<RespuestaWSReasignaLineaCapturaVO>(respuesta,headers, HttpStatus.CREATED);*/
	}
	
	/*COMIENZA CONSULTA*/
	@RequestMapping(value = "/buscarInfraccionesReasignacionHistorico", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCIONES_REASIGNACION_HISTORICO')")
	public ResponseEntity<List<ConsultaInfraccReasignacionHistoricoVO>> buscarInfraccReasignacionHistorico(@RequestParam("fechaInicio") String fechaInicio, 
																				@RequestParam("fechaFin") String fechaFin, @RequestParam("noInfraccion") String noInfraccion,
																				@RequestParam("placaOficial") String placaOficial,
																				@RequestParam("placaVehiculo") String placaVehiculo) throws NotFoundException{
		List<ConsultaInfraccReasignacionHistoricoVO> detalleHistorico = consultaLCService.buscarHistoricoLC(fechaInicio, fechaFin, noInfraccion, placaOficial, placaVehiculo);
		if (detalleHistorico !=null) {
			return new ResponseEntity<List<ConsultaInfraccReasignacionHistoricoVO>>(detalleHistorico, HttpStatus.OK);
		}else {
			throw new NotFoundException("Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/buscarInfraccionesReasignacionEstadistica", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_INFRACCIONES_REASIGNACION_ESTADISTICA')")
	public ResponseEntity<List<ConsultaInfraccReasignacionEstadisticaVO>> buscarInfraccReasignacionEstadistica(@RequestParam("fechaInicio") String fechaInicio, 
																				@RequestParam("fechaFin") String fechaFin, @RequestParam("placaOficial") String placaOficial,
																				@RequestParam("nombreOficial") String nombreOficial) throws NotFoundException{
		try {
		List<ConsultaInfraccReasignacionEstadisticaVO> detalleEstadistica = consultaLCService.buscarEstadisticaLC(fechaInicio, fechaFin, placaOficial, nombreOficial);
		return new ResponseEntity<List<ConsultaInfraccReasignacionEstadisticaVO>>(detalleEstadistica, HttpStatus.OK);
		}catch (Exception e) {
			throw new NotFoundException("Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/buscarDetalleReasignacionesByOficial", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_DETALLE_REASIGNACIONES_BY_OFICIAL')")
    public ResponseEntity<List<DetalleDeReasignacionesOficialVO>> buscaDetalleReasignacionesByOficial(@RequestParam("placaOficial") String placaOficial,
    																								  @RequestParam("fechaInicio") String fechaInicio,
    																								  @RequestParam("fechaFin") String fechaFin){
    	List<DetalleDeReasignacionesOficialVO> detalleReasignaciones = consultaLCService.ConsultaDetalleReasignacionesByOficial(placaOficial, fechaInicio, fechaFin);
		return new ResponseEntity<List<DetalleDeReasignacionesOficialVO>>(detalleReasignaciones, HttpStatus.OK);
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/reporteHistoricos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_REASIGNACION_HISTORICO')")
    public ResponseEntity<byte[]> generarReporteExcelHistoricos()  {
    	
    	Map reporte 					   =  consultaLCService.getReporteExcelHistorico();
    	String filename 				   =  (String) reporte.get("nombre") ;
    	ByteArrayOutputStream outputStream =  (ByteArrayOutputStream) reporte.get("reporte");
    	final byte[] bytes				   = outputStream.toByteArray();

    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;      
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/reporteEstadistica", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_REASIGNACION_ESTADISTICA')")
    public ResponseEntity<byte[]> generarReporteExcelEstadisticas()  {
   
    	Map reporte 					   = consultaLCService.getReporteExcelEstadistica(); 
    	String filename 				   = (String) reporte.get("nombre") ;
    	ByteArrayOutputStream outputStream =  (ByteArrayOutputStream) reporte.get("reporte");
    	final byte[] bytes				   = outputStream.toByteArray();

    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;      
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/reporteEstadisticaPorPersona", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_ESTADISTICA_POR_PERSONA')")
    public ResponseEntity<byte[]> generarReporteExcelEstadisticasPorPersona()  {
   
    	Map reporte 					   = consultaLCService.getReporteExcelEstadisticaPorPersona(); 
    	String filename 				   = (String) reporte.get("nombre") ;
    	ByteArrayOutputStream outputStream =  (ByteArrayOutputStream) reporte.get("reporte");
    	final byte[] bytes				   = outputStream.toByteArray();

    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;      
    }
	
	/***
	 * @author Jesus Gutierrez
	 * @param String jsonReasignacionVO
	 * @return Un objeto VO de la clase ConsultaInfraccionForReasignacionVO
	 */
	private ConsultaInfraccionForReasignacionVO crearInfraccionReasignacionVO(String jsonInfraccionVO){
		ConsultaInfraccionForReasignacionVO infraccionReasignacionVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	infraccionReasignacionVO = mapper.readValue(jsonInfraccionVO.toString(), ConsultaInfraccionForReasignacionVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return infraccionReasignacionVO;
	}
	
	/***
	 * @author Jesus Gutierrez
	 * @param String jsonReasignacionVO
	 * @return Un objeto VO de la clase DetalleDeReasignacionesInfraccionVO
	 */
	private DetalleDeReasignacionesInfraccionVO crearDetalleDeReasignacionesInfraccionVO(String jsonReasignacionVO){
		DetalleDeReasignacionesInfraccionVO detalleDeReasignacionesInfraccionVO = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	detalleDeReasignacionesInfraccionVO = mapper.readValue(jsonReasignacionVO.toString(), DetalleDeReasignacionesInfraccionVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return detalleDeReasignacionesInfraccionVO;
	}

}
