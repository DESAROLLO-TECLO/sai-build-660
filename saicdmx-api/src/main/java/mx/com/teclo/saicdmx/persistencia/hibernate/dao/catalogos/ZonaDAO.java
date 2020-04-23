package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaDTO;

public interface ZonaDAO extends BaseDao<ZonaDTO> {
	ZonaDTO busarZonaPorCodigo(String zonaCod);
}
