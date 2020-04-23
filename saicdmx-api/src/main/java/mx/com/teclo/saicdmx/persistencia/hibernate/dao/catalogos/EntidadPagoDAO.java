package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EntidadPagoDTO;

public interface EntidadPagoDAO extends BaseDao<EntidadPagoDTO> {
	EntidadPagoDTO buscarEntidadPagoPorCod(String entidadPagoCod);
	List<EntidadPagoDTO> busquedaOrdenanda();
}
