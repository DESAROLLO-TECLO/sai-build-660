package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoBusquedaAtcDTO;

public interface TipoBusquedaAtcDAO extends BaseDao<TipoBusquedaAtcDTO> {
	List<TipoBusquedaAtcDTO> buscarTiposBusquedaAtencionCiudadana();
}
