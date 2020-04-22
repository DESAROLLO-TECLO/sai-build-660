package mx.com.teclo.saicdmx.api.rest.liberacionvehiculo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.caja.CajaService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.liberacionvehiculo.LiberacionVehiculoService;
import mx.com.teclo.saicdmx.negocio.service.usuarios.UsuariosService;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ConsultaSalidaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ModificacionSalidaDepositoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class LiberacionVehiculoRestController {

	@Autowired 
	private LiberacionVehiculoService LiberacionVehiculoService; 
	
	@Autowired
	private CajaService cajaService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private UsuariosService usuariosService;
	
	
	@RequestMapping(value = "/validarDeposito", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('VALIDAR_DEPOSITO_EMPLEADO')")
	public ResponseEntity<Long> buscaInformacionAMostrar() throws NotFoundException {
		Long depositoEmpleado = cajaService.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		if(depositoEmpleado == 0){
			throw new NotFoundException("¡Cuidado! No puedes realizar esta operación, verifica tu perfil");
		}
		return new ResponseEntity<Long>(depositoEmpleado, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaSalidaDeposito", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('BUSCAR_VEHICULOS')")
	public ResponseEntity<List<ConsultaSalidaDepositoVO>> buscaDepositoSalida(@RequestParam("tipoBusqueda") String tipoBusqueda, 
																			  @RequestParam("valor") String valor,
																			  @RequestParam("deposito") Long depositoId) throws NotFoundException{
		List<ConsultaSalidaDepositoVO> lista = LiberacionVehiculoService.buscaDepositoSalida(tipoBusqueda, valor, depositoId);
		if(lista.isEmpty()){
			throw new NotFoundException("No se encontraron registros");
		}
		
		return new ResponseEntity<List<ConsultaSalidaDepositoVO>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaSalidaDepositoModificacion/{infracNum}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BUSCAR_SALIDA_DEPOSITO_PARA_MODIFICACION')")
	public ResponseEntity<ModificacionSalidaDepositoVO> consultaLiberacionVehiculoModificacion(@PathVariable("infracNum") String infracNum) throws NotFoundException{
		ModificacionSalidaDepositoVO object = LiberacionVehiculoService.consultaParaLiberacionVehiculoModificacion(infracNum);
		if(object == null){
			throw new NotFoundException("No se encontraron registros");
		}
		return new ResponseEntity<ModificacionSalidaDepositoVO>(object, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/guardarSalidaDeposito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('GUARDAR_SALIDA_DEPOSITO')")
	public ResponseEntity<Map> guardarSalidaDeposito(@RequestParam("objectVO") String objectVO) throws BusinessException, ParseException{
		ModificacionSalidaDepositoVO recibeObject = crearObjetoModificacion(objectVO);
		Long usuarioId = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		
		Map salida = LiberacionVehiculoService.guardarSalidaVehiculo(recibeObject, usuariosService.buscaUsuarioById(usuarioId), usuarioId);
		return new ResponseEntity<Map>(salida, HttpStatus.CREATED);
	}
	
	private ModificacionSalidaDepositoVO crearObjetoModificacion(String jsonDocumentoVO){
		ModificacionSalidaDepositoVO objectUptadte = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
           	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           	objectUptadte = mapper.readValue(jsonDocumentoVO.toString(), ModificacionSalidaDepositoVO.class);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return objectUptadte;
	}
}
