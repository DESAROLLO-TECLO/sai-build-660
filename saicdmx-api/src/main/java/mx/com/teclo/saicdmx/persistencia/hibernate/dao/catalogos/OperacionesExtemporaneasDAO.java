package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;


import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.OperacionesExtemporaneasDTO;

public interface OperacionesExtemporaneasDAO extends BaseDao<OperacionesExtemporaneasDTO> {

	public OperacionesExtemporaneasDTO getFechaDeRegistro(String usuarioId);

}
