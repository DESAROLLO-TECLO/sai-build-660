package mx.com.teclo.saicdmx.persistencia.hibernate.dao.logs;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.WebLogsDTO;
/**
 * 
 * @author javier07
 *
 */
public interface WebLogsDAO extends BaseDao<WebLogsDTO>{
	
	/**
	 * 
	 * @return List<WebLogsDTO>
	 */
	List<WebLogsDTO> busquedaLogsTodos();
	/**
	 * 
	 * @param id
	 * @return WebLogsDTO 
	 */
    WebLogsDTO busquedaLogPorId(Long id);
    /**
     * Obtiene Logs por parametros
     * @author Fernando Castillo
     * @return Long
     * */
    Long ontieneLog(String descripcion, String logNombre,String logTipoArch, String rutaArchivo);
}
