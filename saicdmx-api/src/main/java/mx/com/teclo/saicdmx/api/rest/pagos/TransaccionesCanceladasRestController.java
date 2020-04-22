package mx.com.teclo.saicdmx.api.rest.pagos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaTransccionCanceladaService;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TransaccionesCanceladasVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class TransaccionesCanceladasRestController {
	
	@Autowired
	private ConsultaTransccionCanceladaService transaccionesCanceladasService;
	
	
	@RequestMapping(value = "/pagos/consultaTransaccionesCanceladas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_CANCELADAS')")
	public ResponseEntity<List<TransaccionesCanceladasVO>> consultaAllTransaccionesCanceladas()throws NotFoundException{
		List<TransaccionesCanceladasVO> listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladas();
		
		if (listCancelacion.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<TransaccionesCanceladasVO>>(listCancelacion, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesCanceladasByParametro", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_CANCELADAS_PARAMETRO')")
	public ResponseEntity<List<TransaccionesCanceladasVO>> consultaTransaccionesCanceladasByParametro(@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "parametro") String parametro)throws NotFoundException{
		List<TransaccionesCanceladasVO> listCancelacion=new ArrayList<>();
		
		switch(tipoBusqueda){
		case "INFRACCION":
			listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladasInfraccion(parametro);
			break;
		case "REFERENCIA":
			listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladasReferencia(parametro);
			break;
		case "NUMOPERACION":
			listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladasNumOperacion(parametro);
			break;
		}
		
		if (listCancelacion.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<TransaccionesCanceladasVO>>(listCancelacion, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/consultaTransaccionesCanceladasFechas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_TRANSACCIONES_CANCELADAS_FECHAS')")
	public ResponseEntity<List<TransaccionesCanceladasVO>> consultaTransaccionesCanceladasByFechas(String tipoBusqueda,
			String fechaInicio,String fechaFin)throws NotFoundException{
		
		List<TransaccionesCanceladasVO> listCancelacion= new ArrayList<>();
		
		 switch(tipoBusqueda){
		 case "FECHAINICIO":
			 listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladasFechaInico(fechaInicio);
			 break;
		 case "FECHAFIN":
			 listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladasFechasFin(fechaFin);
			 break;
		 case "FECHAS":
			 listCancelacion=transaccionesCanceladasService.consultaTransaccionesCanceladasFechas(fechaInicio, fechaFin);
			 break;
		 }
		
		 if (listCancelacion.isEmpty())
				throw new NotFoundException("No se encontraron registros");
		
		return new ResponseEntity<List<TransaccionesCanceladasVO>>(listCancelacion, HttpStatus.OK);
	}

}
