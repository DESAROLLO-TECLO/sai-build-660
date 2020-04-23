package mx.com.teclo.saicdmx.api.rest.pagos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.pagos.CancelacionTransaccionService;
import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaTransaccionesService;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class CancelacionTransaccionRestController {
	
	@Autowired
//	@Qualifier("cancelacionTransaccionServices")
	private CancelacionTransaccionService cancelacionTransaccionServices;
	
	@Autowired 
	private ConsultaTransaccionesService consultaTransccionesService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@RequestMapping(value = "/pagos/cancelacionTransaccion", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('CANCELACION_TRANSACCION')")
	public ResponseEntity<ConsultaTransaccionesVO> cancelacionTransaccion(@RequestBody ConsultaTransaccionesVO transaccion)throws NotFoundException{
		ConsultaTransaccionesVO cancelacionVO = new ConsultaTransaccionesVO();
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		cancelacionVO=cancelacionTransaccionServices.cancelacionTransaccion(transaccion,usuario.getId(),transaccion.getCajaId());
		if (cancelacionVO==null)
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<ConsultaTransaccionesVO>(cancelacionVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesCancelacion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_CANCELACION')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaAllTransaccionesCancelacion()throws NotFoundException{
		List<ConsultaTransaccionesVO> listCancelacion=new ArrayList<>();
		listCancelacion=consultaTransccionesService.consultaAllTransaccionesCancelacion();
		if (listCancelacion.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<ConsultaTransaccionesVO>>(listCancelacion, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesCancelacionByParametro", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_CANCELACION_PARAMETRO')")
	public ResponseEntity<List<ConsultaTransaccionesVO>> consultaTransaccionesByParametroCancelacion(@RequestParam(value = "tipoBusqueda") String tipoBusqueda, 
			   @RequestParam(value = "parametro")String parametro)throws NotFoundException{
		List<ConsultaTransaccionesVO> listCancelacion=new ArrayList<>();
		
		switch(tipoBusqueda){
		case "INFRACCION":
			listCancelacion=consultaTransccionesService.consultaTransaccionesByNumInfraccionCancelacion(parametro);
			break;
		case "REFERENCIA":
			listCancelacion=consultaTransccionesService.consultaTransaccionesByNumReferenciaCancelacion(parametro);
			break;
		case "NUMOPERACION":
			listCancelacion=consultaTransccionesService.consultaTransaccionesByNumOperacionCancelacion(parametro);
			break;
		}
		
		if (listCancelacion.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<ConsultaTransaccionesVO>>(listCancelacion, HttpStatus.OK);
	}
	
}
