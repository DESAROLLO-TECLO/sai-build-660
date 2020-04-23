package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.math.BigDecimal;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagosFinanzasDTO;

/**
 * 
 * @author Javier Flores
 *
 */
public interface PagosFinanzasDAO extends BaseDao<PagosFinanzasDTO> {

	/**
	 * @author Javier Flores
	 * @param infracNum
	 * @return BigDecimal
	 */
	public BigDecimal getPagoEnLinea(String infracNum);
	
}
