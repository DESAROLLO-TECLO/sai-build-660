package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaEstatusProcesoDTO;
 


public interface GarantiasEstatusProcesoDAO extends BaseDao<GarantiaEstatusProcesoDTO>{
	
	/**
	 * @author genunt
	 * @param tipoFecha Integer
	 * @param estatusProceso Integer
	 * @param fInicio String 
	 * @param fFin String
	 * @return List<GarantiaEstatusProcesoDTO>
	 */
	List<GarantiaEstatusProcesoDTO> buscaGarantiaPorEstatusYFechaCreacion(Integer tipoFecha, Integer estatusProceso, Date fInicio, Date fFin);
	
	/**
	 * @author genunt
	 * @param garantiaId Long
	 * @param procesoId Integer
	 * @return GarantiaEstatusProcesoDTO
	 */
	GarantiaEstatusProcesoDTO buscaGarantiaEstatusProcesoPagadas(Long garantiaId, Integer procesoId);
}
