package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PropiedadesCompDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface PropiedadesCompDAO extends BaseDao<PropiedadesCompDTO>{

	/**
	 * Descripción: Método para consultar las propiedades de los compoentes
	 * @author Jorge Luis
	 * @param Long
	 * @return List<PropiedadesCompDTO>
	 */
	public List<PropiedadesCompDTO> getComponentesPropiedad(Long idComponente);
	
}
