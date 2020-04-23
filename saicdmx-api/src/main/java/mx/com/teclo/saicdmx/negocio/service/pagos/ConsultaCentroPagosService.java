package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesCanceladasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosTransaccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.RespuestaCentroPagosVO;
import mx.com.teclo.siidf.centrodepagos.mit.vo.Consulta;

/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * 
 * Descripcion					: CentroDePagosService
 * Historial de Modificaciones	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 02/Diciembre/2016
 */
 
public interface ConsultaCentroPagosService {
	/**
	 *  Obtiene totales de transacciones del centro de pagos por fecha .
	 *  @param String  fechaInicio
     *  @param String fechaFin
     *  @param String tipoPago
	 *  @return List<CentroPagosValidaVO>  Lista de entidades.
	 *  
	 */
	public List<RespuestaCentroPagosVO> obtenerRegistroCentroPagos(String fecha) ;
	public List<Consulta> obtenerReferenciasPagosHandheld(String fecha);
	
	/**
	 *  Obtiene una lista de transaciones especificas por numReferencia y fecha de la transaccion.
	 *  @param String  fecha
     *  @param String referencia
	 *  @return List<Consulta>  Lista de transacciones.
	 *  
	 */
	public List<DatosTransaccionVO> obtenerTransaccionesCentroPagos(Date fecha,String referencia);
	
	public ConsultaTransaccionesVO cancelacionTransaccion(String importe,String numOperacion,String numAutoriza);

 	public String getVoucherPago(String numOperacion);
}
