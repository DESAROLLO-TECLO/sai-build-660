package mx.com.teclo.saicdmx.negocio.service.logs;

import java.io.File;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.logs.ComboVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsBusquedaPorIdVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsBusquedaVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsConsultaComboVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
/**
 * 
 * @author javier07
 *
 */
public interface LogsConsultaService {

	/**
	 * Consulta los <b>Logs</b> que se encuntran registrados en la base de datos
	 * según los criterios de búsqueda establecidos.
	 * @author javier07
	 * @return Regresa un objeto de tipo <b>List<LogsVO></b>.
	 */
	public List<LogsBusquedaVO> busquedaLogsTodos();

	/**
	 * Consulta los <b>Logs</b> que se encuntran registrados en la base de datos
	 * según los criterios de búsqueda establecidos.
	 * @author javier07
	 * @return Regresa un objeto de tipo <b>List<LogsVO></b>.
	 */
	public List<LogsConsultaComboVO> busquedaLogsPorPerfil(Long perfilId);

	/**
	 * Consulta los <b>Logs</b> que se encuntran registrados en la base de datos
	 * según el log_id establecido.
	 * @author javier07
	 * @return Regresa un objeto de tipo <b>LogsVO></b>.
	 */
	public LogsBusquedaPorIdVO busquedaLogPorId(Long id);

	/**
	 * Consulta los <b>Perfiles Asignados al Log</b> que se busca.
	 * @author javier07
	 * @return Regresa un objeto de tipo <b>List<ComboVO></b>.
	 */
	public List<ComboVO> perfilesAsignadosPorLog(Long id);

	/**
	 * Consulta los <b>Perfiles Asignados al Log</b> que se busca.
	 *  @author javier07
	 * @return Regresa un objeto de tipo <b>List<ComboVO></b>.
	 */
	public List<ComboVO> perfilesNoAsignadosPorLog(Long id);
	
	/**
	 * @author javier07
	 * @param file
	 * @param logInfo
	 * @param logId
	 * @return obtenerListaArchivosLogs
	 */
	List<LogsVO> obtenerListaArchivosLogs(File file,LogsBusquedaPorIdVO logInfo,Long logId);

}
