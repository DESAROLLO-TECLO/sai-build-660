package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.EstatusInfraccionDTO;

public interface EstatusInfraccionDAO extends BaseDao<EstatusInfraccionDTO> {
	EstatusInfraccionDTO buscarEstatusInfPorCod(String estatatusInfCod);
}
