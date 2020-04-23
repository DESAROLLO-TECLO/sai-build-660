package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;

public interface CatTipoDocumentoDAO extends BaseDao<CatTipoDocumentoDTO>{

	public List <CatTipoDocumentoDTO> catalogoDocumento();
	public List <CatTipoDocumentoDTO> getcatalogoDocumentoID(String listId);
}
