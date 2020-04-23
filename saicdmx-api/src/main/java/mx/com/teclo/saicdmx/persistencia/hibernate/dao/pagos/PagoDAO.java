package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagoDTO;
/**
 *  Copyright (c) 2016, Teclo Mexicana.
 *  Descripcion					: Interfaz PagoDAO
 *  
 *  Historial de Modificaciones	:
 *  Descripcion del Cambio		: Creacion
 *  @author 					: fjmb 
 *  @version 					: 1.0
 *  Fecha						: 29/Octubre/2016
 */
public interface PagoDAO extends BaseDao<PagoDTO>{
	
	/**
	 * Obtiene informacion  de un pago por numero de infraccion
	 * @param   String infracNum , numero de infraccion
	 * @return  PagoDTO 
	 */
	public PagoDTO getPagoByNumInfraccion(String infracNum);
	
	/**
	 * Obtiene informacion  de un pago por numero de infraccion
	 * @param   String infracNum , numero de infraccion y Long tranId Numero unico de la transacción
	 * @return  PagoDTO 
	 */
	public PagoDTO getPagoByNumInfraccionAndTranId(String infracNum,Long tranId);
	
	/**
	 * Obtiene informacion  de un pago por numero de Control Interno (NCI)
	 * @param   String numControl , numero de Control Interno (NCI)
	 * @return  PagoDTO 
	 */
	public PagoDTO getPagoByNumControlAndTranId(String numControl,Long tranId);
	
	public void updatePagoDTO(PagoDTO pagoDTO);
	
	/**
	 *  Metodo para obtener el id pago ó el id caja 
	 *  mediante el número de infracción
	 * @author Javier Flores
	 * @param infracNum , campo
	 * @return Long
	 */
	public Long consultaParamsPago(String infracNum,String campo);
}
