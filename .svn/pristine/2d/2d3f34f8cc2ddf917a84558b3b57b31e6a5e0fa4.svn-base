package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.math.BigDecimal;
import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;

/**
 *  Copyright (c) 2016, Teclo Mexicana. 
 *  
 *  Descripcion					: PagoInfraccionService
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Octubre/2016
 */
 
public interface PagoInfraccionService {

	/**
	 *  Realiza el pago con efectivo.
	 *  @param datosPagoVO datos para realizar el pago.
	 *  @return PagoVO Datos de la entidad que se pago.
	 * @throws ParseException 
	 */
	public PagoVO realizarPagoEfectivo( DatosPagoVO datosPagoVO ) throws ParseException;
	
	/**
	 *  Realiza el pago con tarjeta.
	 *  @param datosPagoVO datos para realizar el pago.
	 *  @return PagoVO Datos de la entidad que se pago.
	 * @throws ParseException 
	 */
	public PagoVO realizarPagoTarjeta( DatosPagoVO datosPagoVO ) throws ParseException;
	
	/**
	 *  Realiza el pago con  Documento.
	 *  @param datosPagoVO datos para realizar el pago.
	 *  @return PagoVO Datos de la entidad que se pago.
	 * @throws ParseException 
	 */
	public PagoVO realizarPagoDocumento( DatosPagoVO datosPagoVO ) throws ParseException;
 
	/**
	 *  Realiza el pago con efectivo.
	 *  @param datosPagoVO datos para realizar el pago.
	 *  @return PagoVO Datos de la entidad que se pago.
	 * @throws ParseException 
	 */
	public PagoVO realizarPagoActaAdministrativa( DatosPagoVO datosPagoVO ) throws ParseException;
	
	/**
	 * Consulta si existe un pago realizado en linea 
	 * @author Javier Flores
	 * @return BigDecimal
	 */
	public BigDecimal buscarActaConPagoEnLinea(String numInfraccion);
	
}
