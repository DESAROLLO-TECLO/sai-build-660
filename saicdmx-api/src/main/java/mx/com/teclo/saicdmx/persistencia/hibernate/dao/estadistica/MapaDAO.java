package mx.com.teclo.saicdmx.persistencia.hibernate.dao.estadistica;

import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;

public interface MapaDAO extends BaseDao<InfraccionDTO>{

	@SuppressWarnings("rawtypes")
	public Map tipoInfraccionPorFecha(String[] tipoInfraccion, String fechaini, String fechafin);
}
