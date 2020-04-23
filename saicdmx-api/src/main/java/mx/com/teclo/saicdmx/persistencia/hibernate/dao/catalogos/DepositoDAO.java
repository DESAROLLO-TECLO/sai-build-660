package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosDTO;

public interface DepositoDAO extends BaseDao<DepositosDTO> {

	/**
	 * @author javier07
	 * @return List<DepositosDTO>
	 */
	List<DepositosDTO> obtenerDepositosActivos();
	
	DepositosDTO buscarDepositoPorCodigo(String depositoCod);

}
