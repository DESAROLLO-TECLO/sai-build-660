package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDepositoDTO;

public interface RegionDepositoDAO extends BaseDao<RegionDepositoDTO> {
	
	List<RegionDepositoDTO> buscarRegionesPorEstado(Long estadoId);
	RegionDepositoDTO buscarRegionesPorCodigo(String regionDepostioCod);
}
