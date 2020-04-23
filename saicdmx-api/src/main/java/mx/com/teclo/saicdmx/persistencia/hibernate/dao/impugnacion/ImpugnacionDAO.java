package mx.com.teclo.saicdmx.persistencia.hibernate.dao.impugnacion;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionImpugnacionDTO;
/**
 * 
 * @author javier07
 *
 */
public interface ImpugnacionDAO extends BaseDao<InfraccionImpugnacionDTO> {

	/**
	 * 
	 * @param tipoBusqueda
	 * @param valor
	 * @return List<InfraccionImpugnacionDTO>
	 */
	List<InfraccionImpugnacionDTO> buscarImppugnacion(String tipoBusqueda, String valor);
}
