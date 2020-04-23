package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosEmpleadosDTO;

public interface DepositoEmpleadoDAO extends BaseDao<DepositosEmpleadosDTO>{

	/**
	 * RETORNA UN REGISTRO DE DEPOSITOS_EMPLEADO FILTRADO POR EL EMP_ID
	 * @author Kevin Ojeda
	 * @param Long id
	 * @return DepositosEmpleadosDTO
	 */
	public DepositosEmpleadosDTO depositoEmpleadoPorEmpId(Long id);

}
