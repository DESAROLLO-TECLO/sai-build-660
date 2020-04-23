package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VehiculoTipoDTO;

public interface VehiculoTipoDAO extends BaseDao<VehiculoTipoDTO> {
	
	public VehiculoTipoDTO buscarVehiculoTipo(Long idVehiculoTipo);

	List<VehiculoTipoDTO> buscarTodosVehiculoTipoActivo();
	
	public List<VehiculoTipoDTO> buscarVehiculoTipoPorSubTipo(Long subTipoId);
	
	VehiculoTipoDTO buscarVehiculoTipoPorCod(String vehiculoTipoCod);

}
