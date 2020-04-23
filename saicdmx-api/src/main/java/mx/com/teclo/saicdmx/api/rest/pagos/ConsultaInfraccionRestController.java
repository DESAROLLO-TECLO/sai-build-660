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

import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaInfraccionService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagadoVO;
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
public class ConsultaInfraccionRestController {

	@Autowired
	private ConsultaInfraccionService consultaInfraccionService;

	@RequestMapping(value = "/pagos/infracciones/deposito", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_PAGO')")
	public ResponseEntity<List<InfraccionDepositoVO>> buscarInfraccionesPago(@RequestParam(value = "tipoParametro") String tipoParametro, 
																		     @RequestParam(value = "valorParametro")String valorParametro) throws NotFoundException {
		List<InfraccionDepositoVO> listaPagoInfraccionVO = new ArrayList<InfraccionDepositoVO>();
//		IngresosDTO traslado = consultaInfraccionService.validarTraslado(tipoParametro,valorParametro, false);
//		
//		if(traslado != null){
//		boolean flagToValidTrasladoDep = consultaInfraccionService.validaDestinoTraslado(traslado.getMovimientos().get(0).getDepDestino());
//		if(flagToValidTrasladoDep){
//			throw new NotFoundException("El vehículo no se ha ingresado en el depósito " +traslado.getMovimientos().get(0).getDepNomDestino() );
//		}else{
//			throw new NotFoundException("El vehículo se encuentra en traslado al depósito " +traslado.getMovimientos().get(0).getDepNomDestino() );
//		}	
//		
//		}else{}
			listaPagoInfraccionVO = consultaInfraccionService.obtenerInfracciones(tipoParametro, valorParametro);
			if (listaPagoInfraccionVO.isEmpty())
				throw new NotFoundException("No se encontraron registros");
		
		
		return new ResponseEntity<List<InfraccionDepositoVO>>(listaPagoInfraccionVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/infracciones/acta", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_PAGO_ACTA')")
	public ResponseEntity<List<InfraccionDepositoVO>> buscarInfraccionesPagoActa(@RequestParam(value = "tipoParametro") String tipoParametro, 
																		         @RequestParam(value = "valorParametro")String valorParametro) throws NotFoundException {

		List<InfraccionDepositoVO> listaPagoInfraccionVO = consultaInfraccionService.obtenerInfraccionesActa(tipoParametro, valorParametro);
		if (listaPagoInfraccionVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<InfraccionDepositoVO>>(listaPagoInfraccionVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/pagos/infracciones/porpagar", method = RequestMethod.GET) 
	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_POR_PAGAR')")
	public ResponseEntity<List<InfraccionPorPagarVO>> buscarInfraccionesPorPagar(@RequestParam(value = "nci") String nci, 
																		         @RequestParam(value = "numInfraccion") String numInfraccion) throws NotFoundException {

		List<InfraccionPorPagarVO> listaInfraccionPorPagarVO = consultaInfraccionService.obtenerInfraccionesPorPagar(nci, numInfraccion);
		if (listaInfraccionPorPagarVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<InfraccionPorPagarVO>>(listaInfraccionPorPagarVO, HttpStatus.OK);
	}
 
	
	@RequestMapping(value = "/pagos/infracciones/pagadasAlDia", method = RequestMethod.GET) 
	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_PAGADAS_DIA')")
	public ResponseEntity<List<InfraccionPorPagarVO>> buscarInfraccionesPagadasPorDia(@RequestParam(value = "parametro") String parametro, 
																		              @RequestParam(value = "valor") String valor, @RequestParam(value = "fecha") String fecha) throws NotFoundException {
		List<InfraccionPorPagarVO> listaInfraccionPorPagarVO = consultaInfraccionService.obtenerInfraccionesPagadasPorDia(parametro, valor, fecha);
		return new ResponseEntity<List<InfraccionPorPagarVO>>(listaInfraccionPorPagarVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/infracciones/porPagaryPagadasAlDia", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_POR_PAGAR_PAGADAS_DIA')")
	public ResponseEntity<List<InfraccionPorPagarVO>> buscarInfraccionesPorPagarYPagadasPorDia(@RequestParam(value = "parametro") String parametro, @RequestParam(value = "nci") String nci, 
	         																				   @RequestParam(value = "numInfraccion") String numInfraccion,  @RequestParam(value = "valor") String valor,
	         																				   @RequestParam(value = "fecha") String fecha) throws NotFoundException {
		List<InfraccionPorPagarVO> listaInfraccion = new ArrayList<InfraccionPorPagarVO>();

		List<InfraccionPorPagarVO> listaInfraccionPagadasDiaVO = consultaInfraccionService.obtenerInfraccionesPagadasPorDia(parametro, valor, fecha);
		
		List<InfraccionPorPagarVO> listaInfraccionPorPagarVO = consultaInfraccionService.obtenerInfraccionesPorPagar(nci, numInfraccion);
 
		if (listaInfraccionPagadasDiaVO.isEmpty() && listaInfraccionPorPagarVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		else
 			  for (InfraccionPorPagarVO infraccionPorPagarVO : listaInfraccionPorPagarVO) {
				  if (listaInfraccionPorPagarVO.size() == 0)
						infraccionPorPagarVO.setUltimoPago(true);
				  
				  listaInfraccionPagadasDiaVO.add(infraccionPorPagarVO);

 			  }
  
		return new ResponseEntity<List<InfraccionPorPagarVO>>(listaInfraccionPagadasDiaVO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pagos/infracciones/pagados", method = RequestMethod.GET) 
	@PreAuthorize("hasAnyAuthority('FIND_INFRAC_PAGADAS')")
	public ResponseEntity<List<PagadoVO>> buscarInfraccionesPagadas (@RequestParam(value = "parametro") String parametro, 
																	 @RequestParam(value = "valor") String valor ) throws NotFoundException {
		List<PagadoVO> listaInfraccionPorPagarVO = consultaInfraccionService.obtenerInfraccionesPagadas (parametro, valor );
		if (listaInfraccionPorPagarVO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		return new ResponseEntity<List<PagadoVO>>(listaInfraccionPorPagarVO, HttpStatus.OK);
	}
 

}
