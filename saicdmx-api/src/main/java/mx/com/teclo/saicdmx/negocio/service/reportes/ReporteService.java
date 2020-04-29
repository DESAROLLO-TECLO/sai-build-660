package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.util.List;


import mx.com.teclo.saicdmx.persistencia.vo.reportes.ReporteVO;

/**
 * 
 * @author javier07
 *
 */
public interface ReporteService {

	/**
	 * @author javier07
	 * @param empledoId
	 * @return
	 */
	List<ReporteVO> obtenerListaLinks(Long empledoId);
	
}
