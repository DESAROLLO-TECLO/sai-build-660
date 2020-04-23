package mx.com.teclo.saicdmx.api.rest.pagos;

import java.math.BigDecimal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.saicdmx.negocio.service.pagos.PagoInfraccionService;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * 
 * Descripcion					: PagoInfraccionRestController
 * Historial de Modificaciones	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 05/Octubre/2016
 */

@RestController
public class PagoInfraccionRestController {

	@Autowired
	private PagoInfraccionService pagoInfraccionService;

	@RequestMapping(value = "/pagos/infraccion/efectivo", method = RequestMethod.PUT) 
	@PreAuthorize("hasAnyAuthority('PAGO_INFRAC_EFECTIVO')")
	public ResponseEntity<PagoVO> pagarInfraccionEfectivo(@RequestBody DatosPagoVO datosPagoVO ) throws NotFoundException, ParseException {
 		
		PagoVO pagoVO =new PagoVO(); 
		try{
			pagoVO = pagoInfraccionService.realizarPagoEfectivo(datosPagoVO);
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new NotFoundException("Ocurrió un problema de comunicación, favor de contactar a su administrador.");
		}
		
 		if (  pagoVO .getResultado() ==null || pagoVO.getResultado().equals("-1") )
			throw new NotFoundException(pagoVO.getMensaje());
	
		return new ResponseEntity<PagoVO>(pagoVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/infraccion/tarjeta", method = RequestMethod.PUT) 
	@PreAuthorize("hasAnyAuthority('PAGO_INFRAC_TARJETA')")
	public ResponseEntity<PagoVO> pagarInfraccionTarjeta(@RequestBody DatosPagoVO datosPagoVO ) throws NotFoundException, ParseException {
 		
		PagoVO pagoVO =new PagoVO();
		try{
			pagoVO = pagoInfraccionService.realizarPagoTarjeta(datosPagoVO);
	}catch(Exception e){
		System.out.println(e.getMessage());
		throw new NotFoundException("Ocurrió un problema de comunicación con el MIT, favor de contactar a su administrador.");
	}

 		if (  pagoVO.getResultado() ==null || pagoVO.getResultado().equals("-1") )
			throw new NotFoundException(pagoVO.getMensaje());
	
		return new ResponseEntity<PagoVO>(pagoVO, HttpStatus.OK);
	}
	@RequestMapping(value = "/pagos/infraccion/documento", method = RequestMethod.PUT) 
	@PreAuthorize("hasAnyAuthority('PAGO_INFRAC_DOCUMENTO')")
	public ResponseEntity<PagoVO> pagarInfraccionDocumento(@RequestBody DatosPagoVO datosPagoVO ) throws NotFoundException, ParseException {
		PagoVO pagoVO =new PagoVO();
		try{
			pagoVO = pagoInfraccionService.realizarPagoDocumento(datosPagoVO);
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new NotFoundException("Ocurrió un problema de comunicación, favor de contactar a su administrador.");
		}
 			
 		if (  pagoVO.getResultado() ==null || pagoVO.getResultado().equals("-1"))
			throw new NotFoundException(pagoVO.getMensaje());
 		
		return new ResponseEntity<PagoVO>(pagoVO, HttpStatus.OK);
	}
 
	@RequestMapping(value = "/pagos/infraccion/acta", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PAGO_INFRAC_ACTA')")
	public ResponseEntity<PagoVO> pagarInfraccionActaAdministrativa(@RequestBody DatosPagoVO datosPagoVO ) throws NotFoundException, ParseException {
		PagoVO pagoVO =new PagoVO();
		try{
		pagoVO = pagoInfraccionService.realizarPagoActaAdministrativa(datosPagoVO);
	}catch(Exception e){
		System.out.println(e.getMessage());
		throw new NotFoundException("Ocurrió un problema de comunicación, favor de contactar a su administrador.");
	}
		
 		if (  pagoVO .getResultado() ==null || pagoVO.getResultado().equals("-1"))
			throw new NotFoundException(pagoVO.getMensaje());
 		
		return new ResponseEntity<PagoVO>(pagoVO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/pagos/infraccion/consultaPagoEnLinea", method = RequestMethod.GET)
	//@PreAuthorize("hasAnyAuthority('PAGO_INFRAC_ACTA')")
	public ResponseEntity<BigDecimal> getActaPagoEnLinea(@RequestParam(value="infracNum")String infracNum){
 				
		BigDecimal respuesta = pagoInfraccionService.buscarActaConPagoEnLinea(infracNum);
		
		return new ResponseEntity<BigDecimal>(respuesta, HttpStatus.OK);
	}
	
	
	
	

}
