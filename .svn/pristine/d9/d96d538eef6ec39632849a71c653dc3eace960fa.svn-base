package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionEstadisticaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesOficialVO;

/***
 * 
 * @author Jesus Gutierrez
 *
 */
public interface ConsultaLCService {
	
	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param noInfraccion
	 * @param placaOficial
	 * @return
	 */
	public List<ConsultaInfraccReasignacionHistoricoVO> buscarHistoricoLC(String fechaInicio, String fechaFin,
														String noInfraccion, String placaOficial, String placaVehiculo);

	
	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param placaOficial
	 * @param nombreOficial
	 * @return
	 */
	public List<ConsultaInfraccReasignacionEstadisticaVO> buscarEstadisticaLC(String fechaInicio, String fechaFin,
			String placaOficial, String nombreOficial);
	
	/***
	 * @author Jesus Gutierrez
	 * @param consultaInfraccReaHistoricoVO
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Map
	 
	@SuppressWarnings("rawtypes")
	public Map generaReporteHistoricoLC (List<ConsultaInfraccReasignacionHistoricoVO> consultaInfraccReaHistoricoVO,
															String fechaInicio, String fechaFin);*/
	
	/***
	 * @author Jesus Gutierrez
	 * @param consultaInfraccReaHistoricoVO
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Map
	 
	@SuppressWarnings("rawtypes")
	public Map generaReporteEstadisiticaLC (List<ConsultaInfraccReasignacionEstadisticaVO> listConsultaInfraccReaEstadisticaVO,
			String fechaInicio, String fechaFin);*/
	
	/**
     * Genera reporte de Excel de linea de captura historico.
     * 
     * @return ByteArrayOutputStream archivo excel.
     */
	@SuppressWarnings("rawtypes")
	public Map getReporteExcelHistorico();
	
	/**
     * Genera reporte de Excel de linea de captura estadistica.
     * 
     * @return ByteArrayOutputStream archivo excel.
     */
	@SuppressWarnings("rawtypes")
	public Map getReporteExcelEstadistica();
	
	/***
	 * Genera reporte de Excel de estadistica de reasignaciones por persona.
	 * @author Jesus Gutierrez
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public Map getReporteExcelEstadisticaPorPersona();
	
	/***
	 * 
	 * @param placaOficial
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public List<DetalleDeReasignacionesOficialVO> ConsultaDetalleReasignacionesByOficial(String placaOficial,
	 																			 		 String fechaInicio, String fechaFin);
}
