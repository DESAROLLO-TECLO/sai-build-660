package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ProgramaDTO;

public interface ProgramaDAO extends BaseDao<ProgramaDTO> {
	
	public List<ProgramaDTO> buscarProgramasActivos();
	
	ProgramaDTO buscarProgramaPorCod(String programaCod);
}
