package mx.com.teclo.saicdmx.api.rest.pagos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.pagos.CentroDePagosService;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosValidaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

@RestController
public class CentroPagosRestController {

	@Autowired
	CentroDePagosService centroDePagosService;
	
	@RequestMapping(value = "/centroPagos/ultimaConsulta", method = RequestMethod.GET ,produces="text/plain" )
	@PreAuthorize("hasAnyAuthority('CENTRO_PAGOS_ULTIMA_CONSULTA')")
	public  @ResponseBody String  ultimaConsultaCentroPagos() throws NotFoundException {
		String fechaUltimaConsulta = centroDePagosService.ultimaConsultaCentroPagos();
		if (fechaUltimaConsulta.isEmpty()) {
			throw new NotFoundException("No se puede obtener la fecha de última consulta al centro de pagos");
		}
 		return fechaUltimaConsulta;

	
	}
	
	@RequestMapping(value = "/centroPagos/pagos/totales", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CENTRO_PAGOS_TOTALES')")
	public ResponseEntity<CentroPagosVO> totalCentroPagosPorTipoYFecha(@RequestParam(value = "fechaInicio") String fechaInicio, 
																	   @RequestParam(value = "fechaFin")String fechaFin) throws NotFoundException {

		CentroPagosVO centroPagosVO= centroDePagosService.totalCentroPagosPorTipoYFecha(fechaInicio, fechaFin);
		if (centroPagosVO.getTotal() == 0)
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<CentroPagosVO>(centroPagosVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/centroPagos/pagos/totales/fechas", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CENTRO_PAGOS_TOTALES_FECHAS')")
	public ResponseEntity<List<CentroPagosVO>> consultaTotalesRangoFecha(@RequestParam(value = "fechaInicio") String fechaInicio, 
																	   @RequestParam(value = "fechaFin")String fechaFin) throws NotFoundException {

		List<CentroPagosVO> listaCentroPagosVO= centroDePagosService.consultaTotalesRangoFecha(fechaInicio, fechaFin);
		if (listaCentroPagosVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<CentroPagosVO>>(listaCentroPagosVO, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/centroPagos/pagos/completos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CENTRO_PAGOS_COMPLETOS')")
	public ResponseEntity<List<CentroPagosValidaVO>> consultaCentroPagosCompleto(@RequestParam(value = "fechaInicio") String fechaInicio, 
																	   @RequestParam(value = "fechaFin")String fechaFin, @RequestParam(value = "tipoPago")String tipoPago) throws NotFoundException {

		List<CentroPagosValidaVO> listaCentroPagosValidaVO= centroDePagosService.getCtroPagosValidaCompletosPorParametros(fechaInicio, fechaFin, tipoPago);
		if (listaCentroPagosValidaVO == null)
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<CentroPagosValidaVO>>(listaCentroPagosValidaVO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/centroPagos/pagos/incompletos", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CENTRO_PAGOS_INCOMPLETOS')")
	public ResponseEntity<List<CentroPagosValidaVO>> consultaCentroPagosInCompleto(@RequestParam(value = "fechaInicio") String fechaInicio, 
																	   @RequestParam(value = "fechaFin")String fechaFin, @RequestParam(value = "tipoPago")String tipoPago) throws NotFoundException {

		List<CentroPagosValidaVO> listaCentroPagosValidaVO= centroDePagosService.getCtroPagosValidaInCompletosPorParametros(fechaInicio, fechaFin, tipoPago);
		if (listaCentroPagosValidaVO == null)
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<CentroPagosValidaVO>>(listaCentroPagosValidaVO, HttpStatus.OK);
	}

}
