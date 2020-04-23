package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.AplicacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ReportesDTO;

public interface ReporteDAO extends BaseDao<ReportesDTO>{
	
	public Long selectMaximo();
	
	public AplicacionDTO idApp();

	/**
	 * Descripción: Método para consultar el reporte por su identificador
	 * @author Jorge Luis
	 * @param Long, String
	 * @return ReportesDTO
	 */
	public ReportesDTO getReporteById(Long idReporte, String cdApp);
	
	/**
	 * Descripción: Método para consultar todos los reportes activos en
	 * en la tabla TAQ004D_AR_REPORTES
	 * @author Jorge Luis
	 * @return EmpleadoEcoVO
	 */
	public List<ReportesDTO> obtrenerReportesActivos(String cdApp);
	
}
