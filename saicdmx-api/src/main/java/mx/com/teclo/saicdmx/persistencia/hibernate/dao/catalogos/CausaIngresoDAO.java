package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CausaIngresoDTO;

public interface CausaIngresoDAO extends BaseDao<CausaIngresoDTO> {

	public CausaIngresoDTO buscarCausaIngreso(Long id);

	public CausaIngresoDTO buscarCausaIngreso(String status, String codigo);

	public List<CausaIngresoDTO> buscarCausaIngresoDiferentesAlId(Long... idCausaIngreso);
	
	public List<CausaIngresoDTO> buscarTodos();
	
	CausaIngresoDTO buscarCausaIngreso(String causaCod);
}
