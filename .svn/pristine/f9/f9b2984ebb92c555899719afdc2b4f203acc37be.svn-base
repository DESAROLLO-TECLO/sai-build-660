package mx.com.teclo.saicdmx.api.rest.semovi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.semovi.ParametroService;
import mx.com.teclo.saicdmx.negocio.service.semovi.SemoviService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviVehiculoRobadoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesConPuntosLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaCPVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.SemoviArchivosLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculoRobadoHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculosRobadosVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

/**
 *
 * @author javier07
 */
@RestController
@RequestMapping("/semoviController")
public class SemoviRestController {

	private static final Logger logger = Logger.getLogger(SemoviRestController.class);

	@Autowired
	private SemoviService semoviService;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ParametroService parametroService;
	
	
	
	
	
	/**
	 * @author javier07
	 * @param fechaArchivo String
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	
	@RequestMapping(value = "generaArchivoPuntoInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('GENERAR_ARCHIVO_PUNTO_INFRACCION')")
	public @ResponseBody Map<String, Object> generaArchivoPuntoInfraccion(@RequestParam("fechaArchivo") String fechaArchivo,
			HttpServletRequest request) {
		String nombreArchivo = "Infracciones";
		Map<String, Object> response = new HashMap<String, Object>();

		
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		List<InfraccionesConPuntosLicenciaVO> lista = semoviService.buscarInfraccPuntosLicencia(fechaArchivo);
		
		response = semoviService.generaArchivoPuntoInfraccion(nombreArchivo, fechaArchivo,
				empleado.getEmpId().toString(), lista);

		return response;
	}

	/**
	 * @author javier07
	 * @param request
	 *            HttpServletRequest
	 * @return Boolean
	 */
	@Transactional
	@RequestMapping(value = "/cargarArchivosSemovi", method = RequestMethod.POST
	,consumes = {"multipart/form-data", MediaType.APPLICATION_JSON_VALUE})
	
	@PreAuthorize("hasAnyAuthority('CARGAR_ARCHIVOS_SEMOVI')")
	public @ResponseBody Map<String, Object> cargarArchivosSemovi(
			@RequestParam("file") MultipartFile[] file,
			@RequestParam("fechaArchivo") String fechaArchivo,
			@RequestParam("tipoArchivo") Integer tipoArchivo) throws BusinessException {
		
		fechaArchivo = fechaArchivo.replaceAll("\"", "");
		
		Map<String, Object> response = new HashMap<String, Object>();
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		Date fechaCreacion = new Date();

		String nombreArchivo = null;
		List<Object> resultado = new ArrayList<Object>();
		Long idUsuario = Long.parseLong(empleado.getEmpId().toString());
		
		try {
			
				if(tipoArchivo == 2){
					nombreArchivo = "PuntosLicencia" + fechaArchivo.replaceAll("/", "") + "-" + rutinasTiempoImpl.getStringDateFromFormta("dd-MM-yyy HH:mm:ss", fechaCreacion).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") +".xlsx";
				}else{
					nombreArchivo = "LicenciaCancelada" + fechaArchivo.replaceAll("/", "") + "-" + rutinasTiempoImpl.getStringDateFromFormta("dd-MM-yyy HH:mm:ss", fechaCreacion).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") +".xlsx";
				}
				
				semoviService.guardaSemoviArchivo(nombreArchivo, fechaArchivo, idUsuario, fechaCreacion, tipoArchivo);
				resultado = semoviService.procesaArchivosSemovi(file[0].getInputStream(), fechaArchivo, idUsuario, tipoArchivo, nombreArchivo,fechaCreacion);
				semoviService.cargaArchivosSemovi(resultado, idUsuario, tipoArchivo);				
				response.put("message", resultado.get(0));
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response.put("error", "error al procesar el archivo");
		}
		
		return response;
	}
 
	/**
	 * @author javier07
	 * @param tipoBusqueda
	 * @param fechaInicio
	 * @param fechaFin
	 * @return List<SemoviArchivosLicenciaVO>
	 */
	@ResponseBody
	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTAR_ARCHIVOS')")
	public List<SemoviArchivosLicenciaVO> cargarInformacion(@RequestParam(value = "tipoBusqueda") Integer tipoBusqueda,
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin) throws NotFoundException{
	
		List<SemoviArchivosLicenciaVO> listaVO = new ArrayList<SemoviArchivosLicenciaVO>();
		listaVO = semoviService.obtenerArchivosLicencia(Long.parseLong(tipoBusqueda.toString()), fechaInicio,
				fechaFin);

		if (listaVO.isEmpty()) {
			throw new NotFoundException("No se encontraron registros");

		}	
		return listaVO;
	}

	/**
	 * @author javier07
	 * @param tipoBusqueda
	 * @param nombreArchivo
	 * @param fechaArchivo
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/descargaExcel", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('DESCARGA_EXCEL')")
	public ResponseEntity<byte[]> descargarReporteExcel(
			                                             @RequestParam(value = "nombreArchivo") String nombreArchivo,
			                                             @RequestParam(value = "fechaArchivo") String fechaArchivo,
			                                             @RequestParam(value = "codigo") String codigo) throws IOException
	                                                   {

		if (codigo.equals("1")) {
			codigo = "SALIDA";
		} else {
			codigo = "ENTRADA";
		}
    
		byte[] bytes = null;
		HttpHeaders headers = new HttpHeaders();
		String rutaArchivo = parametroService.getRutaSemovi() + codigo + "/"+fechaArchivo.substring(6,10)+"/" + nombreArchivo;
		bytes  = semoviService.recuperarArchivo(rutaArchivo);
		if(bytes != null){
	        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
	    	headers.add("Content-Disposition", "attachment; filename="+nombreArchivo);
	    	headers.add("filename",  nombreArchivo);
	        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	        headers.setContentLength(bytes.length);
		}else{
			bytes = new byte[0];
		}
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;   
	}
	
	/**
	 * @author javier07
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/buscaCatTipoArchivos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_TIPO_ARCHIVOS')")
	public @ResponseBody Map<String, Object> buscaCatTipoArchivos() {
		Map<String, Object> response = new HashMap<String, Object>();
			
			response = semoviService.buscaCatTipoArchivos();
		
		return response;
	}

	
	/**
	 * @author javier07
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/buscaCatTipoArchivosActivos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_TIPO_ARCHIVOS_ACTIVOS')")
	public @ResponseBody Map<String, Object> buscaCatTipoArchivosActivos() {
		Map<String, Object> response = new HashMap<String, Object>();
			
			response = semoviService.buscaCatTipoArchivosActivos();
		
		return response;
	}
	
	/**
	 * @author javier07
	 * @param fechaArchivo String
	 * @param codigoArchivo Integer
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/validaArchivoExiste", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDA_ARCHIVO_EXISTE')")
	public @ResponseBody Map<String, Object> validaArchivoExiste(
			@RequestParam("fechaArchivo")String fechaArchivo,
			@RequestParam("codigoArchivo")Integer codigoArchivo) {			
		return semoviService.validaArchivoExiste(fechaArchivo, codigoArchivo);
	}
	
	
//	******* INICIA VEHICULOS ROBADOS****************************
	
	/**
	 * @author dagoberto
	 * @param codigoArchivo Integer
	 * @return List<FilterValuesVO>
	 */
	/*
	@RequestMapping(value = "/buscarOpciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_OPCIONES_VEHICULO_ROBADO')")
	public List<FilterValuesVO>  buscarOpcionesVehRobados() {			
		return semoviService.buscarOpcionesVehRobados();
	}
	
	
	@RequestMapping(value = "/consultaVehRobados", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyAuthority('BUSCAR_VEH_ROBADOS')")
	public List<VehiculosRobadosVO>  buscaVehRobados( @RequestParam("opcion") String opcion,
			@RequestParam("valor") String valor) throws NotFoundException {
		List<VehiculosRobadosVO> response  =	semoviService.buscaVehiculosRobados(opcion, valor);
		if(response.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		return response;
	}
	
	
	
	@RequestMapping(value = "/consultarVehRobados", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_VEHICULOS_ROBADOS')")
	public List<VehiculosRobadosVO>  buscarVehRobados(
			@RequestParam("valor") String valor) throws NotFoundException {
		List<VehiculosRobadosVO> response = null;
		boolean responseValida = semoviService.getExistExpediente(valor);
			if(responseValida == true){
				response =	semoviService.buscarVehiculosRobados(valor);			
			}else{
				throw new NotFoundException("El expediente " +valor+ " no existe. ¿ Desea crearlo ?");
			}
		
		return response;
	}
	
	@RequestMapping(value = "/altaExpediente", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ALTA_VEH_ROBADO_EXPEDIENTE')")
	public  boolean altaExpediente(
			@RequestParam("expedienteNombre") String expediente) throws NotFoundException{
		boolean flagExist = false;
		try {
			UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
			EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());	
			flagExist = semoviService.createExpediente(expediente, empleado.getEmpId());
				if (!flagExist) {
			 		throw new NotFoundException("El expediente no se puede crear");
			 	}
		} catch (Exception e) {
			System.out.println("Hay problemas con el alta del expediente con numero: " + expediente);
		}
		return flagExist;
	}
	
	
	
	@RequestMapping(value = "/consultarVehRobadosHist", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_VEHICULOS_ROBADOS_HIST')")
	public List<VehiculoRobadoHistoricoVO> consultaHistorico(
			@RequestParam("id") Long idRobo){
		return semoviService.consultaHistorico(idRobo) ;
	}	
	

	@RequestMapping(value = "/consultarVehDetalle", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_CONSULTAR_VEH_DETALLE')")
	public VehiculosRobadosVO consultarVehDetalle(
			@RequestParam("id") Long idRobo){
	
		return semoviService.consultaDetalleVehiculo(idRobo) ;
	}
	
	
	@RequestMapping(value = "/buscarModelo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_MODELO_VEHICULO_ROBADO')")
	public List<FilterValuesVO>  buscarModelo() {			
		return semoviService.buscarModelo();
	}
	
	@RequestMapping(value = "/buscarTipo", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_TIPO_VEHICULO_ROBADO')")
	public List<FilterValuesVO>  buscarTipo(@RequestParam("id") Long id) {			
		return semoviService.buscarTipo(id);
	}
	
	
	 @RequestMapping(value = "/guardarVehiculoRobado", method = RequestMethod.GET)
		public ResponseEntity<Boolean> guardarVehiculoRobado(@RequestParam(value="vehiculoRobadoVO")String vehiculoRobadoVO ) throws ParseException {
		 VehiculosRobadosVO convertVO = this.conversionAngularJava(vehiculoRobadoVO);
		 Boolean result = false;
		 try {
			Long resultHist = semoviService.guardarVehiculoRobado(convertVO.getIdEstatus());
			if(resultHist > 0){
				 result = semoviService.guardarDataVehiculoRobado(convertVO,resultHist );
			 }
		} catch (Exception e) {
			System.out.println("No se puede guardar el registro de vehiculo robado");
		}
		 return new ResponseEntity<Boolean>(result,HttpStatus.OK);
			
		}
	 
	 @RequestMapping(value = "/updateVehiculoRobado", method = RequestMethod.GET)
		public ResponseEntity<Boolean> updateVehiculoRobado(@RequestParam(value="vehiculoRobadoVO")String vehiculoRobadoVO ) throws ParseException {
		 VehiculosRobadosVO convertVO = this.conversionAngularJava(vehiculoRobadoVO);
		 	SemoviVehiculoRobadoDTO vehiculoEnRobo = semoviService.updateDataVehiculoRobado(convertVO);
		 if(vehiculoEnRobo != null){
			 semoviService.insertaHistoricoRoboVehiculo(convertVO.getIdRobo(), convertVO.getIdEstatus());
			 vehiculoEnRobo = semoviService.updateDataVehiculoRob(convertVO);
		 }
		 return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			
		}
	 
		@RequestMapping(value = "/buscarVehiculosEstatus", method = RequestMethod.GET)
		public List<FilterValuesVO>  buscarVehiculosEstatus() {			
			return semoviService.buscarVehiculosEstatus();
		}
	 
		 @RequestMapping(value = "/cancelarRegistroRob", method = RequestMethod.GET)
			public ResponseEntity<Boolean> cancelarRegistroRob(@RequestParam(value="idRobo")Long idRobo ) throws ParseException {
			 try {
				 semoviService.cancelaRegistroRobo(idRobo);
			} catch (Exception e) {
				System.out.println("No se puede cancelar el registro");
			}			 
			 return new ResponseEntity<Boolean>(true,HttpStatus.OK);
				
			}
		 
			@RequestMapping(value = "/getReport", method = RequestMethod.GET)
			public ResponseEntity<byte[]> generarReporteExcel()  {
		    	
		    	String filename 				   = "Reporte_de_Robo.xls" ;
		    	ByteArrayOutputStream outputStream = semoviService.generarReporteExcel();
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
			
			
			@RequestMapping(value = "/verifiReporteExist", method = RequestMethod.GET)
			public Long verifiReporteExist(@RequestParam(value="exp")String exp, @RequestParam(value="turno")String turno ) throws NotFoundException {			
				 Long id  = semoviService.verifiReporteExist(exp, turno);
				 if(id == null || id.equals("")){
					 throw new NotFoundException("El expediente no se puede crear");
				 }else{
					 return id; 
				 }
				
			}
			
			@RequestMapping(value = "/updateExistExp", method = RequestMethod.GET)
			public boolean updateExistExp(@RequestParam(value="expNew")String expNew, @RequestParam(value="expOld")String expOld ) throws NotFoundException {			
				boolean flagExistExp = semoviService.getExistExpediente(expNew);
				 if(flagExistExp == true){
					 throw new NotFoundException("El expediente ya existe con este nombre");
					
				 }else{
					 semoviService.updateExistExperiente(expNew, expOld);
					 semoviService.updateVehiculosRobExp(expNew, expOld);
					 return flagExistExp;
				 }
			}
			
	 
		private VehiculosRobadosVO conversionAngularJava (String jsonDocumentoVO){
			VehiculosRobadosVO newObject = null;
	        try {
	        	ObjectMapper mapper = new ObjectMapper();
	           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	           	newObject = mapper.readValue(jsonDocumentoVO.toString(), VehiculosRobadosVO.class);
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return newObject;
		}
	
	
	*/
	
	
	
}
