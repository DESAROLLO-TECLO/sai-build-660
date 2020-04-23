package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface ParametroDAO extends BaseDao<ParametrosDTO>{

	/**
	 * Descripción: Método para consultar los parametros 
	 * de un reporte
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametroDTO>
	 */
	public List<ParametrosDTO> getParametrosByReporte(Long idReporte);
	
	
}
