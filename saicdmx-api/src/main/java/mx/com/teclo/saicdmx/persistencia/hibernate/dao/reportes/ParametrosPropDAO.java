package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosPropDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface ParametrosPropDAO extends BaseDao<ParametrosPropDTO>{


	/**
	 * Descripción: Método para consultar las propiedades de 
	 * cada uno de los parametros
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosPropDTO>
	 */
	public List<ParametrosPropDTO> getParametrosPropiedad(Long idParametro);
	
}
