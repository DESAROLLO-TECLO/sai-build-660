package mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;

public interface ArticuloDAO extends BaseDao<ArticuloDTO> {
	
	public List<ArticuloDTO> buscarNumerosArticulosInfracciones();

	public List<ArticuloDTO> buscarFraccionesArticulo(String numeroArticulo);

	public ArticuloDTO buscarArticulo(Long idArticulo);

	public ArticuloDTO buscarArticulo(String numeroArticulo, String fraccion, String parrafo, String inciso);
	
	List<ArticuloDTO> buscarArticuloActivos();
}
