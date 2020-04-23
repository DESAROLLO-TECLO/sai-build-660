package mx.com.teclo.saicdmx.api.rest.autosRobados;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.semovi.SemoviService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviVehiculoRobadoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculoRobadoHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.semovi.VehiculosRobadosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

/**
 *
 * @author dagoberto
 */
@RestController
@RequestMapping("/autoRobadoController")
public class autosRobadosRestController {

	@Autowired
	private SemoviService semoviService;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Autowired
	private EmpleadoService empleadoService;
	
	
//	******* INICIA VEHICULOS ROBADOS****************************
	
	/**
	 * @author dagoberto
	 * @param codigoArchivo Integer
	 * @return List<FilterValuesVO>
	 */
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
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/consultarVehRobados", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_VEHICULOS_ROBADOS')")
	public ResponseEntity<Map> buscarVehRobados(
			@RequestParam("valor") String valor) throws NotFoundException {
		List<VehiculosRobadosVO> listaData = null;
		Map response = new HashMap<>(); 
		Long responseValida = semoviService.getExistExpediente(valor);
			if(responseValida != null ){
				response.put("idExp", responseValida);
				listaData = semoviService.buscarVehiculosRobados(valor);
				response.put("listData",listaData );			
			}else{
				throw new NotFoundException("El expediente " +valor+ " no existe. ¿ Desea crearlo ?");
			}
		
			return new ResponseEntity<Map>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultarExpediente", method = RequestMethod.GET)
	public List<VehiculosRobadosVO> consultarExpediente(
			@RequestParam("idExp") Long idExp) throws NotFoundException {
		List<VehiculosRobadosVO> response  = semoviService.getExistExpedienteName(idExp);
			if(response.isEmpty()){
				throw new NotFoundException("");
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
				Long flagExistExp = semoviService.getExistExpediente(expNew);
				 if(flagExistExp != null){
					 throw new NotFoundException("El expediente ya existe con este nombre");
					
				 }else{
					 semoviService.updateExistExperiente(expNew, expOld);
					 semoviService.updateVehiculosRobExp(expNew, expOld);
					 return true;
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
	
	
	
	
	
	
}
