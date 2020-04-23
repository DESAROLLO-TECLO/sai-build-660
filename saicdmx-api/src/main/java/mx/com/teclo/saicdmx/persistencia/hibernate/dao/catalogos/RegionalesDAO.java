package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionalesDTO;

public interface RegionalesDAO extends BaseDao<RegionalesDTO> {
	
	public List<RegionalesDTO> buscarRegionales();

}
