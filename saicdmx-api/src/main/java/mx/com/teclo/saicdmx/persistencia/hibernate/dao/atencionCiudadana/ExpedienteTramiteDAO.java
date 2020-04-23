package mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.ExpedienteTramiteDTO;

public interface ExpedienteTramiteDAO extends BaseDao<ExpedienteTramiteDTO> {
	ExpedienteTramiteDTO buscarExpedintePorFolioTipo(String folioTramite, String tipoExp);
}
