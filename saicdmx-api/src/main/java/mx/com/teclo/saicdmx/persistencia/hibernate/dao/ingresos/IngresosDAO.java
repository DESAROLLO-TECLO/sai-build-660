package mx.com.teclo.saicdmx.persistencia.hibernate.dao.ingresos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;

public interface IngresosDAO extends BaseDao<IngresosDTO>{

	/***
	 * @author Jesus Gutierrez
	 * @param infracNumControl
	 * @return
	 */
	public IngresosDTO buscaIngresoByNumeroControl(String infracNumControl);
	
	public IngresosDTO buscaTraslado(String infracNum, String valor, Long idDep, boolean isIngreso);

	public IngresosDTO buscaIngresoByInfraccion(String numinfrac);


}
