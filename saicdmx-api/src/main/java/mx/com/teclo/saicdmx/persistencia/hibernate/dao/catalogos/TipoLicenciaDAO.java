package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoLicenciaDTO;

public interface TipoLicenciaDAO extends BaseDao<TipoLicenciaDTO> {
	
	public List<TipoLicenciaDTO> buscarListaTipoLicencia(String status);

	public TipoLicenciaDTO buscarTipoLicencia(Long idTipoLicencia);

	public TipoLicenciaDTO buscarTipoLicencia(String tipoLicenciaNombre);
	
	TipoLicenciaDTO buscarTipoLicenciaPorCod(String tipoLicenciaCod);
}
