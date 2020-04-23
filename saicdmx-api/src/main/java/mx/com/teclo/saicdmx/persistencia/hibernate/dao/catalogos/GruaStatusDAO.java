package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaStatusDTO;

public interface GruaStatusDAO extends BaseDao<GruaStatusDTO> {
	GruaStatusDTO buscarEstatusGruaPorCod(String estatusGruaCod);
	
}
