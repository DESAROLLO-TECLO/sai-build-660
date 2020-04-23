package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosValidaVO;

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
 
public interface CentroDePagosService {
	
	/**
	 *  Obtiene totales de fallas de pagos en el centro de pagos.
	 *  @param String  fechaInicio
     *  @param String fechaFin
	 *  @return List<CentroPagosVO>  Lista de entidades.
	 *  
	 */
	public  CentroPagosVO totalCentroPagosPorTipoYFecha( String fechaInicio, String fechaFin);
	
	/**
	 * Obtiene la fecha de la ultima consulta al centro de pagos
	 * @return String  Fecha de ultima consulta al centro de pagos.
	 */
	
	public  String 	ultimaConsultaCentroPagos();

	/**
	 *  Obtiene totales de fallas de pagos en entidades locales , por rango de fecha.
	 *  @param String  fechaInicio
     *  @param String fechaFin
	 *  @return List<CentroPagosVO>  Lista de entidades.
	 *  
	 */
	
	public  List<CentroPagosVO> consultaTotalesRangoFecha(  String fechaInicio, String fechaFin );
	
	/**
	 *  Obtiene totales de fallas de pagos en entidades locales , por rango de fecha y pagos incompletos.
	 *  @param String  fechaInicio
     *  @param String fechaFin
     *  @param String tipoPago
	 *  @return List<CentroPagosValidaVO>  Lista de entidades.
	 *  
	 */
	 public List<CentroPagosValidaVO> getCtroPagosValidaInCompletosPorParametros(String fechaInicio, String fechaFin, String tipoPago);
	 
	 /**
	  *  Obtiene totales de fallas de pagos en entidades locales , por rango de fecha y pagos completos.
	  *  @param String  fechaInicio
	  *  @param String fechaFin
	  *  @param String tipoPago
	  *  @return List<CentroPagosValidaVO>  Lista de entidades.
	  *  
	  */
	 
	 public List<CentroPagosValidaVO> getCtroPagosValidaCompletosPorParametros(String fechaInicio, String fechaFin, String tipoPago);
 	 
}
