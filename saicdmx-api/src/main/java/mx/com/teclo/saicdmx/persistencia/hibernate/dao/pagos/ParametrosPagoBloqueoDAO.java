package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ParametrosPagosDTO;

public interface ParametrosPagoBloqueoDAO extends BaseDao<ParametrosPagosDTO>{

	public ParametrosPagosDTO obtenerParametroPago(Integer idParametroPago);
}
