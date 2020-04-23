package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ResponsableVehiculoDTO;

public interface ResponsableVehiculoDAO extends BaseDao<ResponsableVehiculoDTO> {
	
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculo(String status);

	public ResponsableVehiculoDTO buscarResponsableVehiculo(Long idResponsableVehiculo);
	
	
	/**
	 * Retorna registros activos del catalogo RESPONSABLE_VEHICULO
	 * @return List<ResponsableVehiculoDTO>
	 */
	public List<ResponsableVehiculoDTO> buscarResponsableVehiculoConEstatusActivo();
	
	ResponsableVehiculoDTO buscarResponsableVehiculoPorCod(String responsableVehiculoCod);
}
