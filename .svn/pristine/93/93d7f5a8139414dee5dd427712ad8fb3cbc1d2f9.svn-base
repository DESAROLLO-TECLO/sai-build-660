package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AgrupamientosDTO;

public interface AgrupamientosDAO extends BaseDao<AgrupamientosDTO> {
	/***
	 * BUSCAR EL AGRUPAMIENTO ASOCIADO AL IDENTIFICADOR
	 * 
	 * @param idAgrupamiento
	 * @return AgrupamientosDTO
	 */
	public AgrupamientosDTO buscarAgrupamiento(Long idAgrupamiento);

	/***
	 * INSERTA EN LA BASE DE DATOS UN AGRUPAMIENTO
	 * 
	 * @param agrupamientoDTO
	 */
	public void guardarAgrupamiento(AgrupamientosDTO agrupamientoDTO);
	
	AgrupamientosDTO buscarAgrupamientoPorCod(String agrupamientoCod);
	
	List<AgrupamientosDTO> buscarAgrupamientosOrdenados();
}
