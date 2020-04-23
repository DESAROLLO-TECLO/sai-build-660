package mx.com.teclo.saicdmx.persistencia.hibernate.dao.administracion;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.DerechosDTO;

public interface DerechosDAO extends BaseDao<DerechosDTO>{

	/**Metodo para obtener los derechos del usuario
	 * @author Fernando Castillo
	 * @return DerechosDTO
	 * */
	public DerechosDTO getDerechosByUsuId(Long usuId,Long operId);
	
}
