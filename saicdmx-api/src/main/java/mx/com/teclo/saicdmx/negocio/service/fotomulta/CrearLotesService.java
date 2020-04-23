package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotoMultaLotesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

public interface CrearLotesService {

	/***
	 * @author Jesus Gutierrez
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoRadar
	 * @param nombreRadar
	 * @param archivoTipo
	 * @return
	 * @throws BusinessException
	 */
	public Integer validarDeteccionesParaLote(String fechaInicio, String fechaFin, Integer tipoRadar, 
											String nombreRadar, Integer archivoTipo) throws BusinessException;
	
	/***
	 * @author Jesus Gutierrez
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getReporteDeteccionesPorLote();
	
	/***
	 * @author Jesus Gutierrez
	 * @param fechaEmision
	 * @param nombreLote
	 * @param fechaInicio
	 * @param fechaFin
	 * @param anoSalario
	 * @param usuario
	 * @param tipoRadar
	 * @param origenPlaca
	 * @return
	 */
	public FotoMultaLotesVO guardarLote(String fechaEmision, String nombreLote, 
										String fechaInicio, String fechaFin, 
										Integer anoSalario, String usuario, Integer tipoRadar, Integer origenPlaca) throws BusinessException;
	
	/***
	 * @author Jesus Gutierrez
	 * @param loteId
	 * @return Integer
	 */
	public Integer realizarCompletacion(Long loteId) throws BusinessException;
}
