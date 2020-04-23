package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;

public interface SectorDAO extends BaseDao<SectorDTO> {

	public List<SectorDTO> buscarSectores(Long estadoId, String status);

	public SectorDTO buscarSector(long idSector);

	SectorDTO buscarSectorPorCod(String sectorCod);
	
	SectorDTO buscarSectorPorCodDel(String sectorCod, Long estadoId, Long delId);
}
