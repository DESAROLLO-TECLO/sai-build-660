package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface ParametrosTablasDAO extends BaseDao<ParametrosTablasDTO>{

	/**
	 * Descripción: Método para obtener todso los parametros
	 *  que estén asociados a un catalogo
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosTablasDTO
	 */
	public ParametrosTablasDTO getParametrosTablas(Long idParametro);
	
	
	/**
	 * Descripción: Método para obtener el registro 
	 * por su identificador unico
	 * @author Jorge Luis
	 * @param Long
	 * @return ParametrosTablasDTO
	 */
	public ParametrosTablasDTO getParametrosTablaById(Long idParametroTabla);
	
	
}
