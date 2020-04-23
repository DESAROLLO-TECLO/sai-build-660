package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoMarcaDTO;

public interface VehiculoMarcaDAO extends BaseDao<VehiculoMarcaDTO> {
	public List<VehiculoMarcaDTO> buscarVehiculoMarca(String status);

	public VehiculoMarcaDTO buscarVehiculoMarca(Long idVehiculoMarca);

	public VehiculoMarcaDTO buscarVehiculoMarcaPorNombre(String nombreMarca);
	
	VehiculoMarcaDTO buscarVehiculoMarcaPorCod(String VehiculoMarcaCod);
	
	List<VehiculoMarcaDTO> busquedaVehiculoMarcaOrdenada();
}
