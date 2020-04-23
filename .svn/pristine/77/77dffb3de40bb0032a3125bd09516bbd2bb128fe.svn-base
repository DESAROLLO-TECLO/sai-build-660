package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoModeloDTO;

public interface VehiculoModeloDAO extends BaseDao<VehiculoModeloDTO> {
	
	/**
	 * RETORNA UNA LISTA DE VEHICULOS ACTIVOS FILTRADOS POR LA MARCA
	 * @param marcaId
	 * @param status
	 * @return
	 */
	public List<VehiculoModeloDTO> buscarVehiculoModelo(Long marcaId, String status);
	
	
	/**
	 * RETORNA UNA LISTA DE VEHICULOS TODOS FILTRADOS POR LA MARCA
	 * @param marcaId
	 * @return
	 */
	public List<VehiculoModeloDTO> buscarVehiculoModeloTodos(Long marcaId);
	
	VehiculoModeloDTO buscarVehiculoModeloPorCod(String vehiculoModeloCod);

}
