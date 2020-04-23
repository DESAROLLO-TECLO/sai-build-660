package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EventoDTO;

public interface EventoDAO extends BaseDao<EventoDTO> {
	EventoDTO buscarEventoPorCod(String eventoCod);
}
