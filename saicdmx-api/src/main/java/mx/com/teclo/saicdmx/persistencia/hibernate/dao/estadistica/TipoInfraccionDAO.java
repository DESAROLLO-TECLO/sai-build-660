package mx.com.teclo.saicdmx.persistencia.hibernate.dao.estadistica;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.estadistica.TipoInfraccionDTO;

public interface TipoInfraccionDAO extends BaseDao<TipoInfraccionDTO>{
	
	public List<TipoInfraccionDTO> tipoInfracciones();
	String getVistaPorTipo(String infracNum);
	
}
