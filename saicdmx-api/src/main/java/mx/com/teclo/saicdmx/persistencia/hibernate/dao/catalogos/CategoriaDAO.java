package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CategoriaDTO;
	
public interface CategoriaDAO extends BaseDao<CategoriaDTO> {
	
	public List<CategoriaDTO> buscarCategoriasActivas();
	
	CategoriaDTO buscarCategoriaPorCod(String categoriaCod);
}
