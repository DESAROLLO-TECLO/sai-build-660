package mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDigitalTodoDiaDTO;

public interface InfraccionDigitalTodoDiaDAO extends BaseDao<InfraccionDigitalTodoDiaDTO>{

	/**
	 * Retorna un listado de registros de la tabla INFRACCIONES_DIGITAL_TODO_DIA filtrados por status
	 * @author Kevin Ojeda
	 * @param String status
	 * @return InfraccionDigitalTodoDiaDTO
	 */
	public List<InfraccionDigitalTodoDiaDTO> buscarPorEstatus(String status);

	/**
	 * Retorna el total del listado de registros de la tabla INFRACCIONES_DIGITAL_TODO_DIA filtrados por status
	 * @author Kevin Ojeda
	 * @param String status
	 * @return InfraccionDigitalTodoDiaDTO
	 */
	public Long countByStatus(String status);

}
