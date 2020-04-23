package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDao;

public interface PerfilesReportesDAO extends BaseDao<PerfilesReportesDTO>{

	/**
	 * Descripción: Método para consultar todos los perfiles
	 * que están ligados a sus reportes
	 * @author Jorge Luis
	 * @param String
	 * @return List<PerfilesReportesDTO>
	 */
	public List<PerfilesReportesDTO> getReportePerfilAtivos(String cdApp);
	
	/**
	 * Descripción: Método para obtener los pefiles y reportes por los
	 * id´s de las mismas
	 * @author Jorge Luis
	 * @param Long, Long, String
	 * @return PerfilesReportesDTO
	 */
	public PerfilesReportesDTO byReporteAndPerfil(Long perfilId, Long reporteId, String cdApp);
	
	/**
	 * Descripción: Método para obtener los 
	 * reportes por perfil de acceso
	 * @author Jorge Luis
	 * @param Long
	 * @return List<PerfilesReportesDTO>
	 */
	public List<PerfilesReportesDTO> ontenerReportesPorPerfil(Long idPerfil);
	
}
