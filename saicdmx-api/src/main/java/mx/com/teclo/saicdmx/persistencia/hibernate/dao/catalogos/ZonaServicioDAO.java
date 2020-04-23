package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaServicioDTO;

public interface ZonaServicioDAO extends BaseDao<ZonaServicioDTO> {
	ZonaServicioDTO buscarZonaServicioPorCod(String zonaServicioCod);
	List<ZonaServicioDTO> busquedaOrdenada();
}
