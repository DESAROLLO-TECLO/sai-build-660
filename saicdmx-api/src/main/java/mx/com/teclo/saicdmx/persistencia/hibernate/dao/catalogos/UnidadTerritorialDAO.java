package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.UnidadTerritorialDTO;

public interface UnidadTerritorialDAO extends BaseDao<UnidadTerritorialDTO> {
	
	public List<UnidadTerritorialDTO> buscarUnidadesTerritorialesPorSector(Long sectorId);

	public UnidadTerritorialDTO buscarUnidadTerritorial(Long utId, Long secId);
	
	UnidadTerritorialDTO buscarUnidadTerritorialPorCod(String utCod, Long secId);
}
