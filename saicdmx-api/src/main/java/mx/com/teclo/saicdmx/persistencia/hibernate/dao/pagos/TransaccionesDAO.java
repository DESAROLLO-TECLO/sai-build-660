package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosTransaccionVO;

/**
 *  Copyright (c) 2017, Teclo Mexicana.
 *  Descripcion					: Interfaz TransaccionesDAO
 *  
 *  Historial de Modificaciones	:
 *  Descripcion del Cambio		: Creacion
 *  @author 					: DanielUnitis
 *  @version 					: 1.0
 *  Fecha						: 26/Abril/2017
 */
public interface TransaccionesDAO   extends BaseDao<TransaccionDTO>{
	
	public List<ConsultaTransaccionesVO> getAllTransacciones(String cajaId);
	
	public List<ConsultaTransaccionesVO> getTransaccionesFechas(String fechaInicio, String fechaFin,String cajaId);
	
	public List<ConsultaTransaccionesVO> getTransaccionesFechaInicio(String fechaInicio,String cajaId);
	
	public List<ConsultaTransaccionesVO> getTransaccionesFechaFin(String fechaFin,String cajaId);
	
	public List<DatosTransaccionVO> getTransaccionNumInfrac(String infracNum,String cajaId);
	
	public List<DatosTransaccionVO> getTransaccionesNumReferencia(String numReferencia,String cajaId);
	
	public TransaccionDTO getTransaccionByTranID (Long tranId,CajaDTO cajaDTO);
	
	public void updateTransaccionDTO(TransaccionDTO transaccion);
	
	//consulta de transacciones para cancelacion
	
	
	public List<TransaccionDTO> getTransaccionALLForCancelacion();
	
	public List<TransaccionDTO> getTransaccionForCancelacionByNumOpera(String numOperacion);
	
	public List<TransaccionDTO> getTransaccionForCancelacionByNumInfrac(String infracNum);
	
	public List<TransaccionDTO> getTransaccionForCancelacionByNumRefe(String numReferencia);
	
	
	
}
