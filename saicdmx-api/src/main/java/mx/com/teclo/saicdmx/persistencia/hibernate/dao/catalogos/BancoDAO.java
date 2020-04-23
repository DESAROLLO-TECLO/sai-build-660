package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.BancoDTO;

public interface BancoDAO extends BaseDao<BancoDTO> {
	BancoDTO buscarBancoPorCod(String bancoCod);
	List<BancoDTO> busquedaOrdenanda();
}
