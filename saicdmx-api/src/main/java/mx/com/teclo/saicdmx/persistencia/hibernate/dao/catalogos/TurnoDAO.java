package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TurnoDTO;

public interface TurnoDAO extends BaseDao<TurnoDTO> {
	TurnoDTO buscarturnoPorCod(String turnoCod);
}
