package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDocumentoDTO;

public interface GarantiasDocumentoDAO extends BaseDao<GarantiaDocumentoDTO> {
	
	 List<GarantiaDocumentoDTO> buscarTipoDocumentos();

}
