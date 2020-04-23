package mx.com.teclo.saicdmx.api.rest.fotomulta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.ConsultaDeteccionesService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.ConsultaLotesService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.CrearLotesService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.DeteccionesSinProcesarService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.EstadisticaService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.FotomultaCanceladasService;
import mx.com.teclo.saicdmx.negocio.service.fotomulta.FotomultaMarcadoService;
import mx.com.teclo.saicdmx.negocio.service.radarArchivoProcesado.RadarArchivoProcesadosService;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ComboMotivoCancelVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesPorRadarVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.DeteccionesResultadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaDeteccionesRechazadasReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaInfraccionesLiberacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaUsuarioClasificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCanceladasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCatComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaMarcadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ParametrosCancelacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoResumenVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class FotoMultaRestController {

	@Autowired
	private DeteccionesSinProcesarService deteccionesSinProcesarService;
	
	@Autowired
	private ConsultaDeteccionesService consultaDeteccionesService;
	
	@Autowired
	private CrearLotesService crearLotesService;
	
	@Autowired
	private ConsultaLotesService consultaLotesService;
	
	@Autowired 
	private RadarArchivoProcesadosService radarArchivoProcesadosService;
	
	@Autowired 
	private EstadisticaService estadisticaService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private FotomultaMarcadoService fotomultaMarcadoService;
	
	@Autowired
	private FotomultaCanceladasService fotomultaCanceladasService;
	
	
	/*CONSULTAS*/
	@RequestMapping(value = "/buscarDetecciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_DETECCIONES')")
	public ResponseEntity<List<DeteccionesPorRadarVO>> buscarDetecciones(@RequestParam(value = "tipoFecha" ) Integer tipoFecha, @RequestParam(value = "origenPlaca" ) Integer origenPlaca) throws NotFoundException {
		List<DeteccionesPorRadarVO> deteccionesPorRadarVO = this.deteccionesSinProcesarService.buscarDetecciones(tipoFecha, origenPlaca);
		if (deteccionesPorRadarVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<DeteccionesPorRadarVO>>(deteccionesPorRadarVO, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/buscarDeteccionesPorTipoFechaYRadar", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('BUSCAR_DETECCIONES_POR_TIPOFECHA_Y_RADAR')")
	public ResponseEntity<Map> buscarDeteccionesPorTipoFechaYRadar(@RequestParam(value = "tipoFecha" ) Integer tipoFecha, 
																   @RequestParam(value = "codigoRadar" ) String codigoRadar,
																   @RequestParam(value = "origenPlaca" ) Integer origenPlaca,
																   @RequestParam(value = "tipoDeteccion" ) Integer tipoDeteccion) throws NotFoundException {
		Map deteccionesSinProcesarVO = this.deteccionesSinProcesarService.buscarDeteccionesPorTiposFecha(tipoFecha, codigoRadar, origenPlaca, tipoDeteccion);
		if (deteccionesSinProcesarVO.isEmpty()) {
			throw new NotFoundException("No se encontraron detecciones del radar: " + codigoRadar);
		}
		return new ResponseEntity<Map>(deteccionesSinProcesarVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarDeteccionesPorMes", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('BUSCAR_DETECCIONES_POR_MES')")
	public ResponseEntity<List<DeteccionesResultadoVO>> buscarDeteccionesPorMes(@RequestParam(value = "fechaInicio" ) String fechaInicio,
														@RequestParam(value = "origenPlaca" ) Integer origenPlaca,
														@RequestParam(value = "tipoFecha" ) Integer tipoFecha,
														@RequestParam(value = "codigoRadar" ) String codigoRadar) throws NotFoundException, ParseException {
		List<DeteccionesResultadoVO> lista = this.deteccionesSinProcesarService.buscarDeteccionesPorMes(fechaInicio, origenPlaca, tipoFecha, codigoRadar);
		if (lista.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<DeteccionesResultadoVO>>(lista, HttpStatus.OK);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/consultaDeteccionesFotomulta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_DETECCIONES_FOTOMULTA')")
	public ResponseEntity<List<ConsultaDeteccionesVO>> consultaDeteccionesFotomulta(@RequestParam(value = "tipoFecha" ) Integer tipoFecha, 
																					@RequestParam(value = "fechaInicio" ) String fechaInicio,
																					@RequestParam(value = "fechaFin" ) String fechaFin,
																					@RequestParam(value = "estatus" ) Long estatus,
																					@RequestParam(value = "procesado" ) Integer procesado,
																					@RequestParam(value = "radarTipo" ) Integer radarTipo,
																					@RequestParam(value = "radarNombre" ) String nombreRadar,
																					@RequestParam(value = "origenPlaca" ) Integer origenPlaca) throws NotFoundException {
		List<ConsultaDeteccionesVO> deteccionesFotomultaVO = this.consultaDeteccionesService.buscaFotomultasByTipoFechas(tipoFecha, fechaInicio, fechaFin, estatus, procesado, radarTipo, 1, nombreRadar, origenPlaca);
		if(deteccionesFotomultaVO == null){
			throw new NotFoundException("Ha ocurrido un imprevisto! , por favor contacte al administrador");
		}		
		if (deteccionesFotomultaVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		
		
		return new ResponseEntity<List<ConsultaDeteccionesVO>>(deteccionesFotomultaVO, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/generarReporteDetecciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_DETECCIONES')")
	public ResponseEntity<byte[]> generarReporteDetecciones() throws NotFoundException {
		
    	Map data =  consultaDeteccionesService.getReporteDetecciones();
    	final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
    	String filename = (String) data.get("nombre");
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        return response;     
	}
	
	@RequestMapping(value = "/validarDeteccionesParaLote", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('VALIDAR_DETECCIONES_PARA_LOTE')")
	public ResponseEntity<Integer> validarDeteccionesParaLote(@RequestParam(value = "fechaInicio") String fechaInicio, 
														  @RequestParam(value = "fechaFin") String fechaFin,
														  @RequestParam(value = "tipoRadar") Integer tipoRadar,
														  @RequestParam(value = "nombreRadar") String nombreRadar,
														  @RequestParam(value = "origenPlaca") Integer origenPlaca) throws BusinessException {
		Integer numeroDetecciones = crearLotesService.validarDeteccionesParaLote(fechaInicio, fechaFin, tipoRadar, nombreRadar, origenPlaca);
		return new ResponseEntity<Integer>(numeroDetecciones, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/generarReporteDeteccionesPorLote", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_DETECCIONES_POR_LOTE')")
	public ResponseEntity<byte[]> generarReporteDeteccionesPorLote() {
		
    	Map data =  crearLotesService.getReporteDeteccionesPorLote();
    	final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
    	String filename = (String) data.get("nombre");
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        return response;     
	}
	
	@RequestMapping(value = "/crearLote", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CREAR_LOTES_FOTOMULTA')")
	public ResponseEntity<FotoMultaLotesVO> crearLote(@RequestParam(value = "fechaEmision") String fechaEmision, 
														@RequestParam(value = "nombreLote") String nombreLote, 
														@RequestParam(value = "salarioMinimo") Integer salarioMinimo, 
														@RequestParam(value = "fechaInicio") String fechaInicio, 
														@RequestParam(value = "fechaFin") String fechaFin,
														@RequestParam(value = "tipoRadar") Integer tipoRadar,
														@RequestParam(value = "origenPlaca") Integer origenPlaca) throws BusinessException{
		String usuario  = usuarioFirmadoService.getUsuarioFirmadoVO().getId().toString();
		FotoMultaLotesVO fotoMultaLotesVO = crearLotesService.guardarLote(fechaEmision, nombreLote, fechaInicio, fechaFin, salarioMinimo, usuario, tipoRadar, origenPlaca);
		return new ResponseEntity<FotoMultaLotesVO>(fotoMultaLotesVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/realizarComplementacion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REALIZAR_COMPLEMENTACION')")
	public ResponseEntity<Integer> realizarCompletacion(@RequestParam(value = "loteId") Long lote) throws BusinessException{
		Integer result = crearLotesService.realizarCompletacion(lote);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultaLotesFotomulta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_LOTES_FOTOMULTA')")
	public ResponseEntity<List<ConsultaLotesVO>> consultaLotes(@RequestParam(value = "fechaInicio") String fechaInicio,
															   @RequestParam(value = "fechaFin") String fechaFin,
															   @RequestParam(value = "tipoRadar") Long tipoRadar,
															   @RequestParam(value = "estatusProceso") Integer estatusProceso,
															   @RequestParam(value = "tipoFecha") Integer tipoFecha,
															   @RequestParam(value = "origenPlaca") Integer origenPlaca) throws NotFoundException{
		List<ConsultaLotesVO> listLotes = this.consultaLotesService.consultaLotes(fechaInicio, fechaFin, tipoRadar, estatusProceso, tipoFecha, origenPlaca);
		if(listLotes.isEmpty()){
			throw new NotFoundException("No se encontraron registros");	
		}

		return new ResponseEntity<List<ConsultaLotesVO>>(listLotes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cancelacionArchivoFotomulta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CANCELACION_ARCHIVO')")
	public ResponseEntity<RadarArchivoResumenVO> consultaLotes(@RequestParam(value = "lote") Long lote,
															    @RequestParam(value = "motivo") String motivo) throws BusinessException{
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		ArchivoBatchFinanzasVO objectVO = radarArchivoProcesadosService.cargarArchivo(lote.toString());
		objectVO.setModificadoPorId(usuario);
		
		radarArchivoProcesadosService.cancelarArchivoTotal(objectVO, motivo);
		//radarArchivoProcesadosService.cancelarArchivo(objectVO, motivo);
		RadarArchivoResumenVO archivoPorCancelar = this.radarArchivoProcesadosService.buscaRadarArchivoACancelar(objectVO.getArchivoId());
		return new ResponseEntity<RadarArchivoResumenVO>(archivoPorCancelar, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaArchivoACancelar", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GET_RESUMEN_ARCHIVO_A_CANCELAR')")
	public ResponseEntity<RadarArchivoResumenVO> buscaArchivoACancelar(@RequestParam(value = "lote") Long lote) throws NotFoundException{
		RadarArchivoResumenVO archivoPorCancelar = this.radarArchivoProcesadosService.buscaRadarArchivoACancelar(lote);
		if(archivoPorCancelar == null){
			throw new NotFoundException("Error al obtener el archivo");
		}
		return new ResponseEntity<RadarArchivoResumenVO>(archivoPorCancelar, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notificarLiberacion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('NOTIFICAR_LIBERACION_ARCHIVO')")
	public ResponseEntity<Boolean> notificarLiberacion(@RequestParam(value = "lote") Long lote, @RequestParam(value = "tipoProcesows") Integer tipoProceso){
		Boolean estatusWS = this.radarArchivoProcesadosService.notificaLiberacionLote(lote, tipoProceso);
		return new ResponseEntity<Boolean>(estatusWS, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notificarAsignacion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('NOTIFICAR_ASIGNACION_ARCHIVO')")
	public ResponseEntity<Boolean> notificarAsignacion(@RequestParam(value = "lote") Long lote, @RequestParam(value = "tipoProcesows") Integer tipoProceso, 
														@RequestParam(value = "nombreLote") String nombreLote){
		Boolean estatusWS = this.radarArchivoProcesadosService.notificaAsignacionLote(lote, tipoProceso, nombreLote);
		return new ResponseEntity<Boolean>(estatusWS, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/buscaReporteEstadistica", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_REPORTE_ESTADISTICA')")
	public ResponseEntity<Map> buscaReporteEstadistica(@RequestParam(value = "fInicio") String fechaInicio, 
													   @RequestParam(value = "fFin") String fechaFin, 
													   @RequestParam(value = "canceladas") Integer canceladas,
													   @RequestParam(value = "tipoReporte") Integer tipoReporte){
		Map reporte = this.estadisticaService.buscaPrevalidacionesPorTipoReporte(fechaInicio, fechaFin, canceladas, tipoReporte);
		return new ResponseEntity<Map>(reporte, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaDeteccionesRechazadasGeneral", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_DETECCIONES_RECHAZADAS_GENERAL')")
	public ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>> buscaPrevalidacionesRechazadasReporteGeneral(
																	@RequestParam(value = "fInicio") String fechaInicio, 
																	@RequestParam(value = "fFin") String fechaFin, 
																	@RequestParam(value = "canceladas") Integer canceladas) throws NotFoundException{
		
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = estadisticaService.buscaPrevalidacionesRechazadasReporteGeneral(fechaInicio, fechaFin, canceladas);
		
		if(lista.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaDeteccionesRechazadasPrevalidador", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_DETECCIONES_RECHAZADAS_PREVALIDADOR')")
	public ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>> buscaPrevalidacionesRechazadasReportePorPrevalidador(
																		@RequestParam(value = "prevalidador") Long prevalidadorId,															
																		@RequestParam(value = "fInicio") String fechaInicio, 
																		@RequestParam(value = "fFin") String fechaFin, 
																		@RequestParam(value = "canceladas") Integer canceladas,
																		@RequestParam(value = "persona") String nombre) throws NotFoundException{
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = estadisticaService.buscaPrevalidacionesRechazadasReportePorPrevalidador(prevalidadorId, fechaInicio, fechaFin, canceladas, nombre);
		if(lista.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaDeteccionesRechazadasParaReporteGeneralSSP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_DETECCIONES_RECHAZADAS_GENERAL_SSP')")
	public ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>> buscaDeteccionesRechazadasParaReporteGeneralSSP(
																			@RequestParam(value = "fInicio") String fechaInicio, 
																			@RequestParam(value = "fFin") String fechaFin) throws NotFoundException{
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = estadisticaService.buscaDeteccionesRechazadasParaReporteGeneralSSP(fechaInicio, fechaFin);
		if(lista.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaDeteccionesRechazadasPorPersonaSSP", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCA_DETECCIONES_RECHAZADAS_POR_PERSONA_SSP')")
	public ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>> buscaDeteccionesRechazadasParaReportePorPersonaSSP(
																		@RequestParam(value = "placa") String placa,
																		@RequestParam(value = "fInicio") String fechaInicio, 
																		@RequestParam(value = "fFin") String fechaFin,
																		@RequestParam(value = "persona") String nombre) throws NotFoundException{
		List<FotoMultaDeteccionesRechazadasReporteVO> lista = estadisticaService.buscaDeteccionesRechazadasParaReportePorPersonaSSP(placa, fechaInicio, fechaFin, nombre);
		if(lista.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotoMultaDeteccionesRechazadasReporteVO>>(lista, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/generarReporteRendimiento", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_RENDIMIENTO')")
	public ResponseEntity<byte[]> generarReporteRendimiento() {
		
    	Map data =  estadisticaService.getReporteRendimiento();
    	final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
    	String filename = (String) data.get("nombre");
    	
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
	@RequestMapping(value = "/generarReporteDeteccionesRechazadas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_REPORTE_DETECCIONES_RECHAZADAS')")
	public ResponseEntity<byte[]> generarReporteDeteccionesRechazadas() throws NotFoundException {
		
    	Map data =  estadisticaService.getReporteDeteccionesRechazadas();
    	
    	if(data == null){
    		throw new NotFoundException("No hay información para generar el reporte");
    	}else{
    	final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
    	String filename = (String) data.get("nombre");
    	
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
	
	/*FILTROS*/
	@RequestMapping(value = "/filtroTiposFecha", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FILTRO_TIPOS_FECHA')")
	public ResponseEntity<List<FilterValuesVO>> filtroTiposFecha() throws NotFoundException {
		List<FilterValuesVO> filterValues = catalogoService.filterTiposFecha();
		return new ResponseEntity<List<FilterValuesVO>>(filterValues, HttpStatus.OK);
	}
	/**
	 * @author javier07
	 * @return List<DeteccionesPorRadarVO>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/buscarDeteccionesPorRangoTiempo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTES_FOTOMULTAS_CONSULTA_DETECCIONES')")
	public ResponseEntity<List<DeteccionesPorRadarVO>> buscarDeteccionesRangoTiempo() throws NotFoundException {
		List<DeteccionesPorRadarVO> deteccionesPorRadarVO = this.deteccionesSinProcesarService.buscarDeteccionesPorRangoTiempo();
		if (deteccionesPorRadarVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}
		return new ResponseEntity<List<DeteccionesPorRadarVO>>(deteccionesPorRadarVO, HttpStatus.OK);
	}     
	
	/**
	 * @author javier07
	 * @return List<DeteccionesPorRadarVO>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/buscarAceptadasRechazadas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTES_FOTOMULTAS_ACEPTADAS_RECHAZADAS')")
	public ResponseEntity<List<DeteccionesPorRadarVO>> buscarAceptadasRechazadas() throws NotFoundException {
		List<DeteccionesPorRadarVO> deteccionesPorRadarVO = this.deteccionesSinProcesarService.buscarAcepRechaPorRangoTiempo();
		if (deteccionesPorRadarVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}
		return new ResponseEntity<List<DeteccionesPorRadarVO>>(deteccionesPorRadarVO, HttpStatus.OK);
	}
	
	/**
	 * @author javier07
	 * @return List<List<FotoMultaInfraccionesLiberacionesVO>>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/buscarLiberacionesInfracciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTES_FOTOMULTAS_LIBERACIONES')")
	public ResponseEntity<List<List<FotoMultaInfraccionesLiberacionesVO>>>buscarLiberaciones() throws NotFoundException {
		List<List<FotoMultaInfraccionesLiberacionesVO>> lista = this.deteccionesSinProcesarService.buscarInfraccLiberacionesPorRangoTiempo();
		if (lista.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<List<FotoMultaInfraccionesLiberacionesVO>>>(lista,HttpStatus.OK);
	}
	
	/**
	 * @author javier07
	 * @return List<FotoMultaUsuarioClasificacionVO>
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/buscarUsuariosClasificacion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTES_FOTOMULTAS_USUARIOS_CLASIFICACION')")
	public ResponseEntity<List<FotoMultaUsuarioClasificacionVO>>buscarUsuariosClasificacion() throws NotFoundException {
		List<FotoMultaUsuarioClasificacionVO> lista = this.deteccionesSinProcesarService.buscarUsuariosClasificacion();
		if (lista.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotoMultaUsuarioClasificacionVO>>(lista,HttpStatus.OK);
	}
	
	/**
	 * @author  Javier Flores
	 * @param fecha  							
	 * @return Map<String, List<FotomultaMarcadoVO>>
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/busquedaDetecPrevalCancel", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_GET_DETECCIONES')")
	public ResponseEntity<Map<String, List<FotomultaMarcadoVO>>[] >buscarFotomulta(@RequestParam(value="fecha") String fecha,
																			   	@RequestParam(value="tipoRadar")	int tipoRadar,
																			   	@RequestParam(value="origenPlaca")int origenPlaca) throws NotFoundException {
		Map<String, List<FotomultaMarcadoVO>>[] mapArreglo =fotomultaMarcadoService.obtenerDeteccionesParaCancel(fecha, tipoRadar, origenPlaca);
		if(mapArreglo[0].isEmpty() && mapArreglo[1].isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<Map<String, List<FotomultaMarcadoVO>>[]> (mapArreglo, HttpStatus.OK);
	}
	
	/**
	 * @author javier07
	 * @param fecha  							
	 * @return List<FotomultaMarcadoVO>
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/getlistaValidaciones", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_GET_VALIDACIONES')")
	public ResponseEntity< List<FotomultaMarcadoVO>> obtenerDetecciones() throws NotFoundException {
		List<FotomultaMarcadoVO> listaVO = fotomultaMarcadoService.obtenerValidadas();		
		if(listaVO==null){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotomultaMarcadoVO>> (listaVO, HttpStatus.OK);
	}

	
	/**
	 * @author javier07
	 * @param fecha  							
	 * @return List<FotomultaMarcadoVO>
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/getlistaPrevalidaciones", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_GET_PREVALIDACIONES')")
	public ResponseEntity<List<FotomultaMarcadoVO>> obtenerPrevalidaciones() throws NotFoundException {		
		List<FotomultaMarcadoVO> listaVO  = fotomultaMarcadoService.obtenerPrevalidaciones();
		if(listaVO==null){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotomultaMarcadoVO>> (listaVO, HttpStatus.OK);
	}

	
	/**
	 * @author javier07
	 * @return List<FotomultaCatComboFechasVO>
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/obtenerFechasCombo", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_GET_LISTA_FECHAS')")
	public ResponseEntity<List<FotomultaCatComboFechasVO>> obtenerFechasCombo() throws NotFoundException {
		
		List<FotomultaCatComboFechasVO> listaFechasVO=fotomultaMarcadoService.obtieneListaFechas();
		if(listaFechasVO.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<List<FotomultaCatComboFechasVO>> (listaFechasVO, HttpStatus.OK);
	}
	/**
	 * @author javier07
	 * @param fecha
	 * @return long
	 */
	@RequestMapping(value = "/cancelarDetecciones", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_CANCELAR')")
	public ResponseEntity<Long>cancelarDetecciones(@RequestParam(value="tipoRadar")	int tipoRadar,
			   									  @RequestParam(value="origenPlaca")int origenPlaca,
			   									  @RequestParam(value="motivoCancelacion")String motivoCancelacion,
			   									  HttpServletRequest request) {
		
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		Long resultado = fotomultaMarcadoService.cancelarDetecciones(tipoRadar,origenPlaca,motivoCancelacion,usuario); 
		return new ResponseEntity<Long> (resultado, HttpStatus.OK);
	}
	
	/**
	 * @author javier07
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/descargarExcelMarcado", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CANCELACION_DETECCIONES_DESCARGA_EXCEL')")
	public ResponseEntity<Void> descargarReporteCanceladas(
			HttpServletRequest request,
			HttpServletResponse response){
		
		byte[] reporte  = fotomultaMarcadoService.descargaExcelDeteccMarcado();
		
		response.setHeader("Content-type", "application/xls");
	    response.setHeader("Content-Disposition", "attachment; filename="+"ReporteDetecciones.xls");
	    response.setContentLength(reporte.length);
	    try {
			response.getOutputStream().write(reporte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
		} catch (IOException e) {
		}
        return new ResponseEntity<Void>( HttpStatus.NOT_FOUND);
	}
	
	/**
	 * @author Javier Flores
	 * @return List<ComboMotivoCancelVO>
	 */
	@RequestMapping(value="/obtenerCatMotCancelacion",method = RequestMethod.GET ,produces = "application/json")
	@PreAuthorize("hasAnyAuthority('FOTOMULTAS_CANCELADAS_GET_CAT_MOT_CANCEL')")
	public @ResponseBody List<ComboMotivoCancelVO> obtenerCatMotivoCancelacion(){
		return fotomultaCanceladasService.obtenerCatMotivoCancel();
	}
	
	@RequestMapping(value="/obtenerFechasCancelacion" ,method = RequestMethod.GET ,produces = "application/json")	
	@PreAuthorize("hasAnyAuthority('FOTOMULTAS_CANCELADAS_GET_FECHAS_CANCELACION')")
	public @ResponseBody List<ComboFechasVO> obtenerFechasCancelacion(){
		return fotomultaCanceladasService.obtenerFechasCancelacion();
	}
	
	@RequestMapping(value="/obtenerDeteccionesCanceladas",method = RequestMethod.POST)	
	@PreAuthorize("hasAnyAuthority('FOTOMULTAS_CANCELADAS_GET_LISTA_DETECCIONES')")
	public ResponseEntity <Map<String, List<FotomultaCanceladasVO>>> consultarDeteccionesCanceladas(
			@RequestBody ParametrosCancelacionVO parametrosCancelacionVO) throws NotFoundException {
		Map<String, List<FotomultaCanceladasVO>>listaVO=fotomultaCanceladasService.consultarDeteccionesCanceladas(parametrosCancelacionVO);
		
		if (listaVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");
		}	
		return new ResponseEntity<Map<String, List<FotomultaCanceladasVO>>>(listaVO, HttpStatus.OK);
	}
	
	/**
	 * @author Javier Flores
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/descargarExcelCanceladas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FOTOMULTAS_CANCELADAS_DESCARGAR_REPORTE')")
	public void descargarReporte(
			HttpServletRequest request,
			HttpServletResponse response){
		
		byte[] reporte  = fotomultaCanceladasService.descargaExcelDetecciones();
		
		response.setHeader("Content-type", "application/xls");
	    response.setHeader("Content-Disposition", "attachment; filename="+"ReporteDeteccionesCanceladas.xls");
	    response.setContentLength(reporte.length);
	    try {
			response.getOutputStream().write(reporte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
		} catch (IOException e) {
		}
	}
	
	
}
