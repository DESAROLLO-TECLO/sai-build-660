package mx.com.teclo.saicdmx.persistencia.hibernate.dao.semovicattipoarchivo;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi.SemoviCatTipoArchivoDTO;

public interface SemoviCatTipoArchivoDAO extends BaseDao<SemoviCatTipoArchivoDTO> {
	
	/**
	 * @author UnitisDes0416
	 * @return SemoviCatTipoArchivoDTO
	 */
	SemoviCatTipoArchivoDTO buscaTipoInfraccionPuntos();
	
	/**
	 * @author UnitisDes0416
	 * @return SemoviCatTipoArchivoDTO
	 */
	SemoviCatTipoArchivoDTO buscaTipoActivoPuntosLicencia();
	
	/**
	 * @author UnitisDes0416
	 * @return SemoviCatTipoArchivoDTO
	 */
	SemoviCatTipoArchivoDTO buscaTipoActivoLicenciaCancelada();
	
	/**
	 * @author UnitisDes0416
	 * @return Lis<SemoviCatTipoArchivoDTO>
	 */
	List<SemoviCatTipoArchivoDTO> buscaCatTipoArchivosActivos();

	Object findOrderByDesc();
}
