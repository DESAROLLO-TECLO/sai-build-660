package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicontrollicencia;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviControlLicenciasDTO;

public interface SemoviControlLicenciasDAO extends BaseDao<SemoviControlLicenciasDTO> {
	
	/**
	 * @author UnitisDes0416
	 * @param folioLicencia String
	 * @return SemoviControlLicenciasDTO
	 */
	SemoviControlLicenciasDTO buscaLicenciaPorFolioLicencia(String folioLicencia);
}

