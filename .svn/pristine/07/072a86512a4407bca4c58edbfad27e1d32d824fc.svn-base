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

import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaInfraccionService;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
 
/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * 
 * Descripcion					: CatPagoInfraccionRestController
 * Historial de Modificaciones	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 05/Octubre/2016
 */

@RestController
public class CatPagoInfraccionRestController {

	@Autowired
	private ConsultaInfraccionService consultaInfraccionService;

	
	@RequestMapping(value = "/pagos/tipoBusqueda", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_TIPO_BUSQUEDA_INFRAC')")
	public ResponseEntity<List<FilterValuesVO>> obtenerTipoBusquedaInfraccion() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(consultaInfraccionService.obtenerTipoBusquedaInfraccion(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/tipoBusquedaActa", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_TIPO_BUSQUEDA_INFRAC')")
	public ResponseEntity<List<FilterValuesVO>> obtenerTipoBusquedaInfraccionActa() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(consultaInfraccionService.obtenerTipoBusquedaInfraccionActa(), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/pagos/tipoPago", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_TIPO_PAGO_INFRAC')")
	public ResponseEntity<List<FilterValuesVO>> obtenerTiposPagoInfraccion() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(consultaInfraccionService.obtenerTiposPagoInfraccion(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/entidadesPago", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_ENTIDADES_PAGO_INFRAC')")
	public ResponseEntity<List<FilterValuesVO>> obtenerEntidadesPagoInfraccion() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(consultaInfraccionService.obtenerEntidadesPagoInfraccion(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/perfil", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_PERFIL_PAGO')")
	public @ResponseBody boolean  consultarPerfilCajero( @RequestParam(value = "placa") String numPlaca ) throws NotFoundException {
		return consultaInfraccionService.obtenerPerfilCajero(numPlaca);
	}

	@RequestMapping(value = "/pagos/mit/token", method = RequestMethod.GET ,produces="text/plain" )
 	public  @ResponseBody String   consultarTokenMit() throws NotFoundException {
		String token = consultaInfraccionService.obtenerTokenMit();
		if (token.isEmpty()) {
			throw new NotFoundException("No se pudo obtener el token");
		}
 		return token;

		 
	}
	
	@RequestMapping(value = "/pagos/tipoBusquedaTransaccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('TIPO_BUSQUEDA_TRANSACCION')")
	public ResponseEntity<List<FilterValuesVO>> obtenerTipoBusquedaTransacciones() throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(consultaInfraccionService.obtenerTipoBusquedaTransacciones(), HttpStatus.OK);
	}
}
