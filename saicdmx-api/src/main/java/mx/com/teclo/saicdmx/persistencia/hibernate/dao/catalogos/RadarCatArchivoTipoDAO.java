package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;


import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RadarCatArchivoTipoDTO;

/**
 * 
 * @author UnitisDes0416
 *
 */
public interface RadarCatArchivoTipoDAO extends BaseDao<RadarCatArchivoTipoDTO> {
	
	/**
	 * @author UnitisDes0416
	 * @return List<RadarCatArchivoTipoDTO>
	 */
	public List<RadarCatArchivoTipoDTO> buscaCatArchivoTipoActivos();
}
