package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ConsultaDeteccionesVO;

public interface ConsultaDeteccionesService {

	/***
	 * @author Jesus Gutierrez
	 * @param tipoFecha
	 * @param fechaInicio
	 * @param fechaFin
	 * @param autorizado
	 * @param procesado
	 * @param radarTipo
	 * @param valido
	 * @param nombreRadar
	 * @param origenPlaca
	 * @return
	 */
	public List<ConsultaDeteccionesVO> buscaFotomultasByTipoFechas(Integer tipoFecha, String fechaInicio, String fechaFin, 
																		Long autorizado, Integer procesado,
																		Integer radarTipo, Integer valido, String nombreRadar,
																		Integer origenPlaca);
	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param autorizado
	 * @param procesado
	 * @param radarTipo
	 * @param tipoFechaBusqueda
	 * @return
	 
	@SuppressWarnings("rawtypes")
	public Map generarReporteDetecciones(String fechaInicio, 
										 String fechaFin, 
										 Long autorizado, 
										 Integer procesado, 
										 Integer radarTipo, 
										 Integer tipoFechaBusqueda);*/
	
	/***
	 * @author Jesus Gutierrez
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getReporteDetecciones();
}
