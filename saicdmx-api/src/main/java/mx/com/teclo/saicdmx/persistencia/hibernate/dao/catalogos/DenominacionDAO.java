package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DenominacionDTO;

public interface DenominacionDAO extends BaseDao<DenominacionDTO> {
	DenominacionDTO buscarDenominacionPorCod(String denominacionCod);
}
