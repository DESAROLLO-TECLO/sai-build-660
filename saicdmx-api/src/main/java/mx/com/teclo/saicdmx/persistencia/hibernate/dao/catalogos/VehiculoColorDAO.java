package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoColorDTO;

public interface VehiculoColorDAO extends BaseDao<VehiculoColorDTO> {
	
	public List<VehiculoColorDTO> buscarColor(String status);

	public VehiculoColorDTO buscarColor(Long idVehiculoColor);
	
	VehiculoColorDTO buscarVehiculoColorPorCod(String vehiculoColorCod);
}
