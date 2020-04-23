package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface ParametrosColumnDAO extends BaseDao<ParametrosColumnDTO>{

	/**
	 * Descripción: Método para obtener todas las columnas
	 * que estén asociados a una tabla y a su ves esta 
	 * tabla pertene a un parametro
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getAllColumns(Long idParametroTabla);
	
	
	/**
	 * Descripción: Método para obtener todos 
	 * registros que dependen de la columna actual
	 * @author Jorge Luis
	 * @param Long
	 * @return List<ParametrosColumnDTO>
	 */
	public List<ParametrosColumnDTO> getHijos(Long idParametroTabla);

	
}
