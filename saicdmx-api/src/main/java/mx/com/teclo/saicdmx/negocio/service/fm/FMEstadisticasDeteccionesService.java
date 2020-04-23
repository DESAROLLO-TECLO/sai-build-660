package mx.com.teclo.saicdmx.negocio.service.fm;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMEstadisticasDeteccionesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

public interface FMEstadisticasDeteccionesService {
	/***
	 * @author José Castillo
	 * @param loteId
	 * @param usuario
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, Object> estadisticasDetecciones(Integer switchRangoFecha, Integer periodoFecha, 
			String fechaInicio, String fechaFin,  Integer tipoDeteccion,  Integer tipoRadar, 
			Integer origenPlaca, String estatusproceso, String nombreRadar, String nombreDeteccion,
			String opcion) throws BusinessException;
	
	@SuppressWarnings("rawtypes")
	public Map getReporteDetecciones(String opcion);
}
