package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoIngresoDTO;

public interface TipoIngresoDAO extends BaseDao<TipoIngresoDTO> {

	public TipoIngresoDTO buscarPorId(Long idTipoIngreso);

	public List<TipoIngresoDTO> buscarDiferentesAlId(Long idTipoIngreso);
	
	TipoIngresoDTO buscarTipoPorCod(String tipoCod);
}
