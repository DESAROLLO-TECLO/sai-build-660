package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicatmotivocancelacion;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviCatMotivoCancelacionDTO;

public interface SemoviCatMotivoCancelacionDAO extends BaseDao<SemoviCatMotivoCancelacionDTO> {
	
	/**
	 * @author UnitisDes0416
	 * @return SemoviCatMotivoCancelacionDTO
	 */
	SemoviCatMotivoCancelacionDTO buscaCancelacionPorArchvioCanceladas();
}
