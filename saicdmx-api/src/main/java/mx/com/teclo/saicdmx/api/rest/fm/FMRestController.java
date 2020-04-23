package mx.com.teclo.saicdmx.api.rest.fm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.fm.ConsultaArchivoOrigenService;
import mx.com.teclo.saicdmx.negocio.service.fm.FMCrearLotesService;
import mx.com.teclo.saicdmx.negocio.service.fm.FMEstadisticasDeteccionesService;
import mx.com.teclo.saicdmx.negocio.service.radarArchivoProcesado.RadarArchivoProcesadosService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fm.FMLotesDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoOrigenVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesDisponiblesFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMEstadisticasDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLotesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoEstatusProcesoArchivoVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.DeteccionesIncorrectasVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ListaDeteccionesErroneas;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class FMRestController {
	
	@Autowired
	private FMCrearLotesService crearLotesService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private FMEstadisticasDeteccionesService estadisticasDeteccionesService;
	@Autowired
	private RadarArchivoProcesadosService radarArchivoService;
	@Autowired
	private ConsultaArchivoOrigenService consultaArchivoOrigenService;
	
	@RequestMapping(value = "/validarDeteccionesParaLoteFM", method = RequestMethod.GET, produces = "application/json")
	// @PreAuthorize("hasAnyAuthority('FM_VALIDAR_DETECCIONES_PARA_LOTE')")
	public ResponseEntity<Map<String, String>> validarDeteccionesParaLote(
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin, 
			@RequestParam(value = "tipoPersona") Integer tipoPersona,
			@RequestParam(value = "nombrePersona") String nombrePersona,
			@RequestParam(value = "tipoDeteccion") Integer tipoDeteccion,
			@RequestParam(value = "nombreDeteccion") String nombreDeteccion,
			@RequestParam(value = "origenPlaca") Integer origenPlaca) 
		throws BusinessException, NotFoundException {
		try {
			Map<String, String> response = crearLotesService.validarDeteccionesParaLote(fechaInicio, fechaFin, tipoPersona,
					nombrePersona, tipoDeteccion, nombreDeteccion, origenPlaca);
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	// @PreAuthorize("hasAnyAuthority('FM_GENERAR_REPORTE_DETECCIONES_POR_LOTE')")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/generarReporteDeteccionesPorLoteFM", method = RequestMethod.GET)
	public ResponseEntity<byte[]> generarReporteDeteccionesPorLote(
			@RequestParam(value = "fechaInicio") String fechaInicio, 
			@RequestParam(value = "fechaFin") String fechaFin, 
			@RequestParam(value = "tipoDeteccion") Integer tipoDeteccion, 
			@RequestParam(value = "tipoPersona") Integer tipoPersona, 
			@RequestParam(value = "nombrePersona") String nombrePersona, 
			@RequestParam(value = "nombreDeteccion") String nombreDeteccion, 
			@RequestParam(value = "origenPlaca") Integer origenPlaca)
		throws Exception, BusinessException, NotFoundException{
		try {
			Map data = crearLotesService.getReporteDeteccionesPorLote(fechaInicio,fechaFin,tipoDeteccion,
					tipoPersona,nombrePersona,nombreDeteccion,origenPlaca);
			final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
			String filename = (String) data.get("nombre");
	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
			headers.add("Content-Disposition", "attachment; filename=" + filename);
			headers.add("filename", filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);
	
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	// ****************************************************************************************//
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/generarArchivoZIP_RAD_ERR", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generarArchivoZIP_RAD_ERR(
			@RequestBody ListaDeteccionesErroneas detErrores)
		throws Exception, BusinessException, NotFoundException{
		// @RequestBody DeteccionesIncorrectasVO[] json)
		// ListaDeteccionesErroneas detErrores = null;
		// (ListaDeteccionesErroneas) crearObjetoDeJSON(json,
		// ListaDeteccionesErroneas.class);
		try {
			List<DeteccionesIncorrectasVO> listdE = new ArrayList<DeteccionesIncorrectasVO>();
	
			for (int i = 0; i < detErrores.getDeteccionesErroeas().size(); i++) {
				System.out.println(detErrores.getDeteccionesErroeas().get(i).getApellidomat());
				listdE.add(detErrores.getDeteccionesErroeas().get(i));
			}
			Map data = radarArchivoService.crearExcelErrores(listdE);
	
			final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
			String filename = (String) data.get("nombre");
	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.add("Content-Disposition", "attachment; filename=" + filename + ".xls");
			headers.add("filename", filename + ".xls");
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);
	
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private Object crearObjetoDeJSON(String jsonDocumentoVO, Class classType) 
		throws Exception, BusinessException, NotFoundException{
		Object objectUptadte = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectUptadte = mapper.readValue(jsonDocumentoVO, classType);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
		return objectUptadte;
	}
	// ****************************************************************************************//
	
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/crearLoteFM", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('FM_CREAR_LOTES')")
	public ResponseEntity<FMLotesVO> crearLote(@RequestParam(value = "fechaEmision") String fechaEmision,
			@RequestParam(value = "nombreLote") String nombreLote,
			@RequestParam(value = "salarioMinimo") Integer salarioMinimo,
			@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin,
			@RequestParam(value = "tipoPersona") Integer tipoPersona,
			@RequestParam(value = "tipoDeteccion") Integer tipoDeteccion,
			@RequestParam(value = "origenPlaca") Integer origenPlaca,
			@RequestParam(value = "stLCaptura") Integer stLCaptura, @RequestParam(value = "stVCP") Integer stVCP)
		throws Exception, BusinessException, NotFoundException{
		String mensajeErr = "";
		try {
			String usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId().toString();
			Long idLote = 0L;
			
			Map<String, String> parametros = crearLotesService.getParametrosLP();
			Integer paramCrearComplemLotesSimul = Integer.parseInt(parametros.get("CREAR_COMPLEM_LOTES_SIMUL"));
			FMLotesDTO lote = crearLotesService.consultaLoteEnProceso();
			if(
				(lote == null && paramCrearComplemLotesSimul == 1) ||
				(lote != null && paramCrearComplemLotesSimul == 1) ||
				(lote == null && paramCrearComplemLotesSimul == 0)
			){
				idLote = crearLotesService.creacionLote(fechaEmision, nombreLote, fechaInicio, fechaFin,
						salarioMinimo, usuario, tipoPersona, tipoDeteccion, origenPlaca, stLCaptura, stVCP);
				//System.out.println("Lote creado: " + idLote);
				if(idLote > 0){
					FMLotesVO fMLoteVO = crearLotesService.crearLoteDetalle(idLote, fechaEmision, nombreLote, fechaInicio, fechaFin,
						salarioMinimo, usuario, tipoPersona, tipoDeteccion, origenPlaca, stLCaptura, stVCP, mensajeErr);
					
					return new ResponseEntity<FMLotesVO>(fMLoteVO, HttpStatus.OK);
				} else {
					mensajeErr = "¡Ha ocurrido un imprevisto! , porfavor contacte al administrador";
					throw new NotFoundException("");
				}
			}else {
				mensajeErr = "Existe un lote en proceso de complementación\nLote ID: " + lote.getId() 
						+ "\nNombre: " + lote.getNombre() + "\nEspere a que termine el proceso";
				throw new NotFoundException("");
			}
		} catch (Exception e) {
			if(mensajeErr != null && !mensajeErr.isEmpty() && !mensajeErr.equals(null)) {
				throw new NotFoundException(mensajeErr);
			} else {
				e.printStackTrace();
				throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
			}
		}
	}
	
	@RequestMapping(value = "/consultaLotesCreadosFM", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('FM_CONSULTA_LOTES_CREADOS')")
	public ResponseEntity<List<FMLotesVO>> consultaLotesCreados() 
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMLotesVO> fmLotesVO = crearLotesService.consultaLotesCreados();
			return new ResponseEntity<List<FMLotesVO>>(fmLotesVO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/realizarComplementacionFM", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('FM_REALIZAR_COMPLEMENTACION')")
	public ResponseEntity<Integer> realizarCompletacion(@RequestParam(value = "idLote") Long idLote)
		throws Exception, BusinessException, NotFoundException{
		String mensajeErr = "";
		Long usuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		Integer result = crearLotesService.realizarCompletacion(mensajeErr, idLote, usuario);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estadisticasDetecciones", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('FM_ESTATADISTICAS_DETECCIONES')")
	public ResponseEntity<Map<String, Object>> estadisticasDetecciones(
			@RequestParam(value = "switchRangoFecha") Integer switchRangoFecha,
			@RequestParam(value = "periodoFecha") Integer periodoFecha,
			@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin,
			@RequestParam(value = "tipoDeteccion") Integer tipoDeteccion,
			@RequestParam(value = "tipoRadar") Integer tipoRadar,
			@RequestParam(value = "origenPlaca") Integer origenPlaca,
			@RequestParam(value = "estatusproceso") String estatusproceso,
			@RequestParam(value = "nombreRadar") String nombreRadar,
			@RequestParam(value = "nombreDeteccion") String nombreDeteccion,
			@RequestParam(value = "opcion") String opcion)
		throws Exception, BusinessException, NotFoundException{
		try {
			// String usuario =
			// usuarioFirmadoService.getUsuarioFirmadoVO().getId().toString();
			Map<String, Object> respuesta = estadisticasDeteccionesService.estadisticasDetecciones(switchRangoFecha,
					periodoFecha, fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca, estatusproceso, nombreRadar,
					nombreDeteccion, opcion);
			// Integer fMLoteVO = 1;
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/generarReporteDeteccionesEstadisticas", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('FM_GENERAR_REPORTE_DETECCIONES_ESTADISTICAS')")
	public ResponseEntity<byte[]> generarReporteDeteccionesEstadisticas(
			@RequestParam(value = "opcion") String opcion) 
		throws Exception, BusinessException, NotFoundException{
		try {
			Map data = estadisticasDeteccionesService.getReporteDetecciones(opcion);
			final byte[] bytes = ((ByteArrayOutputStream) data.get("reporte")).toByteArray();
			String filename = (String) data.get("nombre");
	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.add("Content-Disposition", "attachment; filename=" + filename);
			headers.add("filename", filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);
	
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/consultaDetDisponiblesFC", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyAuthority('FM_CONSULTA_LOTES_CREADOS')")
	public ResponseEntity<List<FMDeteccionesDisponiblesFCVO>> consultaDetDisponibles() 
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMDeteccionesDisponiblesFCVO> fmDeteccionesDisponiblesFCVO = crearLotesService.consultaDetDisponibles();
			return new ResponseEntity<List<FMDeteccionesDisponiblesFCVO>>(fmDeteccionesDisponiblesFCVO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/consultaNombreSugLoteFC", method = RequestMethod.GET, produces = "text/plain")
	public ResponseEntity<String> consultaNombreSugLote(
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin, 
			@RequestParam(value = "tipoPersona") Integer tipoPersona,
			@RequestParam(value = "tipoDeteccion") Integer tipoDeteccion)
		throws Exception, BusinessException, NotFoundException{
		try {
			String nombreSugLote = crearLotesService.consultaNombreSugLote(fechaInicio, fechaFin, tipoPersona,
					tipoDeteccion);
			return ResponseEntity.ok().header("Custom-Header", "foo").body(nombreSugLote);
			// return new ResponseEntity<String>(nombreSugLote, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	/*
	 * Servicio de consulta Archivo origen
	 * 
	 * 
	 */
	
	@RequestMapping(value = "/consultaArchivoOrigen", method = RequestMethod.POST)
	 @PreAuthorize("hasAnyAuthority('CONSULTA_ARCHIVO_ORIGEN_PARAMETROS')")
	public ResponseEntity<List<FMConsultaArchivoOrigenVO>> ConsultaArchivoOrigenTodoRest(
			@RequestParam(value = "switchRangoFecha") Integer switchRangoFecha,
			@RequestParam(value = "periodoFecha") Integer periodoFecha,
			@RequestParam(value = "fechaInicio") String fechaInicio, 
			@RequestParam(value = "fechaFin") String fechaFin,
			@RequestParam(value = "estatusproceso") String estatusProceso,
			@RequestParam(value = "tipoArchivo") String tipoArchivo)
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMConsultaArchivoOrigenVO> listaCatalogoTipoProceso = consultaArchivoOrigenService
					.obtenerConsultaArchivoOrigen(switchRangoFecha, periodoFecha, fechaInicio, fechaFin, estatusProceso,
							tipoArchivo);
			return new ResponseEntity<List<FMConsultaArchivoOrigenVO>>(listaCatalogoTipoProceso, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/consultaArchivoOrigenTodo", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('CONSULTA_ARCHIVO_ORIGEN_TODO')")
	public ResponseEntity<List<FMConsultaArchivoOrigenVO>> ConsultaArchivoOrigenTodo()
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMConsultaArchivoOrigenVO> listaArchivoTodo = consultaArchivoOrigenService.consultaArchivoOrigenTodo();
			return new ResponseEntity<List<FMConsultaArchivoOrigenVO>>(listaArchivoTodo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/consultaArchivoDetecciones", method = RequestMethod.GET)
	 @PreAuthorize("hasAnyAuthority('CONSULTA_ARCHIVO_DETECCIONES')")
	public ResponseEntity<List<FMConsultaArchivoDeteccionesVO>> ConsultaArchivoDetecciones(
			@RequestParam(value = "idArchivo") Integer idArchivo)
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMConsultaArchivoDeteccionesVO> listaArchivoDetecciones = consultaArchivoOrigenService
				.obtenerConsultaArchivoDeteccion(idArchivo);
			return new ResponseEntity<List<FMConsultaArchivoDeteccionesVO>>(listaArchivoDetecciones, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/reporteArchivoDetecciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('REPORTE_DETECCIONES_ARCHIVO')")
	public ResponseEntity<byte[]> reporteArchivoDetecciones(
			@RequestParam(value = "idArchivo") Integer idArchivo)
		throws Exception, BusinessException, NotFoundException{
		try{
			List<FMConsultaArchivoOrigenVO> listaArchivo = consultaArchivoOrigenService.obtenerConsultaPorId(idArchivo);
			String filename = listaArchivo.get(0).getNb_Archivo();
			ByteArrayOutputStream outputStream = consultaArchivoOrigenService.generarReportExcelDetecciones(
					idArchivo, filename);
			final byte[] bytes = outputStream.toByteArray();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
			headers.add("Content-Disposition", "attachment; filename=" + filename);
			headers.add("filename", filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);
			
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			
			return response;
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/reporteArchivoOrigenExcel", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('REPORTE_EXCEL_CONSULTA')")
	public ResponseEntity<byte[]> reporteArchivoOrigenExcel(@RequestParam(value = "opcion") Integer opcion,
			@RequestParam(value = "busqueda") Boolean busqueda,
			@RequestParam(value = "switchRangoFecha") Integer switchRangoFecha,
			@RequestParam(value = "periodoFecha") Integer periodoFecha,
			@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin,
			@RequestParam(value = "estatusproceso") String estatusProceso,
			@RequestParam(value = "tipoArchivo") String tipoArchivo)
		throws Exception, BusinessException, NotFoundException{
		try{
			String filename = "ArchivosReportadosConsulta.xls";
			ByteArrayOutputStream outputStream = consultaArchivoOrigenService.generarReportExcelArchivoOrigen(opcion,
				busqueda, switchRangoFecha, periodoFecha, fechaInicio, fechaFin, estatusProceso, tipoArchivo);
			
			final byte[] bytes = outputStream.toByteArray();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
			headers.add("Content-Disposition", "attachment; filename=" + filename);
			headers.add("filename", filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setContentLength(bytes.length);
	
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/cancelaArchivoOrigen", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CANCELA_ARCHIVO_ORIGEN')")
	public ResponseEntity<Integer> cancelaArchivoOrigen(
			@RequestParam(value = "idArchivo") Integer idArchivo)
		throws Exception, BusinessException, NotFoundException{
		try{
			Integer valor = consultaArchivoOrigenService.updateCancelar(idArchivo);
			return new ResponseEntity<Integer>(valor, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/descargaArchivoOrigen", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DESCARGA_ARCHIVO_ORIGEN')")
	public ResponseEntity<InputStreamResource> descargaArchivo(
			@RequestParam(value = "idArchivo") Integer idArchivo,
			HttpServletResponse response)
		throws Exception, BusinessException, NotFoundException{
		
			List<FMConsultaArchivoOrigenVO> listaArchivo = consultaArchivoOrigenService.obtenerConsultaPorId(idArchivo);
			InputStreamResource isr = null;
			int error = 0;
			File file = new File(listaArchivo.get(0).getNb_Original());
			
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.setContentType(new MediaType("application", "force-download"));
			respHeaders.setContentLength(file.length());
			response.setHeader("Content-disposition",
					"attachment; filename=\"" + listaArchivo.get(0).getNb_Archivo() + ".txt\"");
			response.setHeader("filename", listaArchivo.get(0).getNb_Archivo());
			
		try {
			isr = new InputStreamResource(new FileInputStream(file));
		} catch (Exception e) {
			error = 1;
			System.out.println("No se encontro ruta especificada: " + e);
		}
		
		if (error == 1) {
			throw new NotFoundException("No se encontro archivo,consultar a soporte");
		}
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}
	//
	// private RadarComboTipoArchivoVO generaCombo(String label, String value) {
	// RadarComboTipoArchivoVO listaComboArchivoVO = new RadarComboTipoArchivoVO();
	//
	// listaComboArchivoVO.setLabel(label);
	// listaComboArchivoVO.setValue(value);
	// return listaComboArchivoVO;
	// }
	
	@RequestMapping(value = "/getComboArchivoEstatusProceso", method = RequestMethod.GET)
	public ResponseEntity<List<FMTipoEstatusProcesoArchivoVO>> getComboArchivoEstatusProceso()
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMTipoEstatusProcesoArchivoVO> listaComboEstatus = consultaArchivoOrigenService.comboEstatusProceso();
			return new ResponseEntity<List<FMTipoEstatusProcesoArchivoVO>>(listaComboEstatus, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/getComboArchivoOrigenTipo", method = RequestMethod.GET)
	public ResponseEntity<List<FMTipoEstatusProcesoArchivoVO>> getComboArchivoTipo()
		throws Exception, BusinessException, NotFoundException{
		try {
			List<FMTipoEstatusProcesoArchivoVO> listaComboArchivo = consultaArchivoOrigenService.comboTipoArchivoOrigen();
			return new ResponseEntity<List<FMTipoEstatusProcesoArchivoVO>>(listaComboArchivo, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
	
	@RequestMapping(value = "/crearLoteValidaciones", method = RequestMethod.GET)
	public ResponseEntity<Long> crearLoteValidaciones(
			@RequestParam(value = "idLote") Long idLote)
		throws Exception, BusinessException, NotFoundException{
		try {
			crearLotesService.crearLoteValidaciones(idLote);
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw new NotFoundException("¡Ha ocurrido un imprevisto! , porfavor contacte al administrador");
		}
	}
}
