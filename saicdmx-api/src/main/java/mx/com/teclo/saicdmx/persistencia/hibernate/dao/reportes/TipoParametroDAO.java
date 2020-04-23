package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoParametroDTO;

public interface TipoParametroDAO extends BaseDao<TipoParametroDTO>{
	
	public List<TipoParametroDTO> listaTiposParametro();

}
