package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.FechasIngresosDTO;

public interface FechasIngresosDAO extends BaseDao<FechasIngresosDTO> {

	public List<FechasIngresosDTO> obtenerFecha(Date fechaIngreso);

	public void guardarFechaIngreso(FechasIngresosDTO fechasIngresosDTO);

}
