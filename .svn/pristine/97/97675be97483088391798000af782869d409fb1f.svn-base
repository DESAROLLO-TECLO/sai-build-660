package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaDeteccionesRechazadasReporteVO;

public interface EstadisticaService {

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param canceladas
	 * @return Map
	 */
	
	@SuppressWarnings("rawtypes")
	Map buscaPrevalidacionesPorTipoReporte(String fechaInicio, String fechaFin,  Integer canceladas, Integer tipoReporte);

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param canceladas
	 * @return
	 */
	List<FotoMultaDeteccionesRechazadasReporteVO> buscaPrevalidacionesRechazadasReporteGeneral(String fechaInicio, String fechaFin,  Integer canceladas);

	/***
	 * @author Jesus Gutierrez
	 * @param prevalidadorId
	 * @param fechaInicio
	 * @param fechaFin
	 * @param canceladas
	 * @param persona
	 * @return List<FotoMultaDeteccionesRechazadasReporteVO>
	 */
	List<FotoMultaDeteccionesRechazadasReporteVO> buscaPrevalidacionesRechazadasReportePorPrevalidador(Long prevalidadorId, String fechaInicio, String fechaFin,  Integer canceladas, String persona);
	
	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	List<FotoMultaDeteccionesRechazadasReporteVO> buscaDeteccionesRechazadasParaReporteGeneralSSP(String fechaInicio, String fechaFin);
	
	/***
	 * @author Jesus Gutierrez
	 * @param placa
	 * @param fechaIncio
	 * @param fechaFin
	 * @param persona
	 * @return List<FotoMultaDeteccionesRechazadasReporteVO>
	 */
	List<FotoMultaDeteccionesRechazadasReporteVO> buscaDeteccionesRechazadasParaReportePorPersonaSSP(String placa, String fechaInicio, String fechaFin, String persona);
	
	/***
	 * @author Jesus Gutierrez
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	Map getReporteRendimiento();
	
	/***
	 * @author Jesus Gutierrez
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	Map getReporteDeteccionesRechazadas();
}
