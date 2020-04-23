package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface ParamPropScriptDAO extends BaseDao<ParamPropScriptDTO>{

	/**
	 * Descripción: Método para consultar el
	 *  subquery que se ejecutará en la vista previa
	 * @author Jorge Luis
	 * @param Long
	 * @return ParamPropScriptDTO
	 */
	public ParamPropScriptDTO getParamPropById(Long idParamtrosProp);
	
}
