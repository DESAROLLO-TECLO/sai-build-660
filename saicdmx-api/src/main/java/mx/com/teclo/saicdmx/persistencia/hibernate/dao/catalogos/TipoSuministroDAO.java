package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoSuministroDTO;

public interface TipoSuministroDAO extends BaseDao<TipoSuministroDTO> {
	
	public List<TipoSuministroDTO> buscarTipoSuministro();
	
	
}
